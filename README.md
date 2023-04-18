# Práctica Desarrollo Empresas

## Explicación del Modelo de Dominio

### Clases:
- Bill 
- BillLine 
- User (MonthlyUser y NormalUser) 
- Series 
- Categorie (con sus descendientes) 
- Season 
- Chapter


### Relaciones:

- Series ---- Categorie

Aunque una Serie tiene solo una Categoría en un momento dentro del sistema, dicha Serie puede cambiar de categoría a lo largo del tiempo (cuando una serie es nueva normalmente pertenece a una categoría más cara), por lo que en el histórico, tiene múltiples categorías. Por esta misma explación, una misma categoría puede "contener" muchas series (al menos 1, ya que no tendría sentido tener una Categoría en el sistema de la que no forme parte ninguna Serie). 
Así mismo, Categoría tiene una subclase por cada tipo de categoría en el sistema. Categoría tiene de atributo el precio, que es el mismo para todas las series de la categoría, por eso es a nivel de clase.

- Series ---- Season ---- Categorie

Estas 3 clases tienen relación de composición entre sí, de manera que si una serie deja de existir en el sistema, también dejarían de existir sus temporadas y sus capítulos. Aunque no aparece en la descripción del sistema específicamente, he decidido almacenar en el capítulo la fecha de emisión. De esta forma, se puede obtener fácilmente la fecha de emisión de la temporada a la que pertenece y también de la propia serie (buscando el primer capítulo).

- Bill ---- BillLine ---- Chapter

En la factura se guarda la fecha de emisión (el mes y el año para ser más específico) y el estado de la factura. He dividido dicho estado en: en progreso, pendiente y pagada. Cuando se crea una factura, su estado inicial es en progreso. Cuando finaliza el mes, pasa a estar a estado pendiente hasta que se paga.
La factura tiene varias líneas y esto se representa con la clase BillLine. Esta clase BillLine recoge información del capítulo visto y de la  temporada y serie a la que pertenece. Si la factura se elimina del sistema, también se elimina la línea de factura. Un capítulo puede encontrarse  en una línea de factura o en muchas, o puede no haber sido visto por nadie.

- Bill ---- User

Un usuario puede tener 0 o muchas facturas en el sistema. Aunque solo tenga 1 factura (o más, no se especifica en el enunciado) pendiente, las demás facturas que ya ha pagado deben estar guardadas en el sistema. Dentro del sistema hay diferentes tipos de usuarios. El usuario mensual tiene una tasa fija al mes. Aunque esto podría describirse también con por ejemplo un booleano y alguna otra variable más, creo que esta solución es más elegante y más escalable.

- User ---- Series / User ---- Chapter

El usuario tiene 3 tipos de listas con series. Una lista de series pendientes, otra de empezadas y otra de finalizadas. Esto se modela con relaciones a diferencia de el estado de la factura, ya que la factura pertenece al usuario pero la serie es independiente del mismo, y si se modelase con un estado, significaría que esa serie tiene ese estado en todo el sistema. El usuario tiene a su vez una serie de capítulos vistos, para tener en cuenta cuando se ha acabado una temporada o una serie y marcarlo como tal.
Un capítulo puede no haber sido visto por ningún usuario o por varios.


## Explicación del código

- Clase User

    - Identificador 

    Utilizar la contraseña como parte del identificador no es recomendable, debido a que puede visualizarse dependendiendo del diseño 
    que se siga en la aplicación REST en ciertas partes, como en las urls de identificación de recursos. Por otro lado, el sistema
    puede tener más de 1 usuario con el mismo nombre. El resto de atributos tampoco considero que sean apropiados, por lo que he 
    elegido generar un identificador aleatorio para esta clase.

    - Anotaciones JPA

    Para la lista de series pendientes, series empezadas, series terminadas y facturas la anotación escogida es
    _@OneToMany_, debido a que un Usuario puede tener 0 o varias series pendientes, empezadas y/o terminadas y también 0 o varias
    facturas.
    La anotación _mappedBy = "user"_ en la propiedad _bills_ indica que la propiedad _"user"_ que se encuentra en la Entidad _Bill_
    es la que mapea la relación. Por tanto, habrá una columna de clave externa que apunte al usuario de la factura para todas las 
    facturas.
    Para los capítulos vistos la relación considerada es _@ManyToMany_ bidireccional. Por una parte el Usuario tiene 0 o varios   
    capítulos vistos y, por otra parte, he considerado guardar qué usuarios han visto qué capítulo. He decidido que sea la entidad 
    Chapter la que gestione la relación (por ello el 'mappedBy = "usersWhoWhatched" en la entidad User).

    - Herencia

    En este caso he decidido aplicar el patrón Single Table Inheritance. De este modo, solo conservo la entidad User y añado una
    columna para indicar el precio de la tarifa del usuario. En el caso de ser un usuario mensual este campo tendrá un valor, mientras
    que en el caso de ser un usuario normal, este campo estará a null.

- Clase Series

    - Identificador 

    Considerando que dos series pueden tener el mismo nombre pero ser distintas y que crear un identificador compuesto con el nombre y
    cualquier otro atributo no me ha parecido buena idea, he decido generar un identificador de manera aleatoria.
    
    - Anotaciones JPA

    La relación desde Serie a Categorie es _@OneToMany_ bidireccional. La serie pertenece a una categoría y una categoría contiene
    numerosas series. La anotación _mappedBy_ indica que la relación está siendo gestionada por la propiedad _categorie_ en la
    entidad Series. 
    La relación entre Serie y Season es _@OneToMany_ bidireccional. La serie contiene varias temporadas y una temporada
    pertenece a solo una serie. La anotación _mappedBY = "series"_ indica que la entidad Season es la que contendrá una columna de 
    clave externa que apuntará a la serie a la que pertenece la temporada. Por otro lado, cualquier operación realizada sobre la 
    serie, se verá reflejada en sus temporadas y su eliminación supondrá la eliminación de estas.

- Clase Season

    - Identificador 

    Una serie no puede tener dos temporadas con el mismo nombre. Por tanto se ha construido un identificador compuesto formado por 
    el atributo _name_ y el atributo _series_.
    
    - Anotaciones JPA

    La relación entre Season y Chapter es _@OneToMany_ bidireccional. La temporada contiene varios capítulos y una capítulo
    pertenece a solo una temporada. La anotación _mappedBY = "season"_ indica que la entidad Chapter es la que contendrá una column
    de clave externa que apuntará a la temporada a la que pertenece el capítulo. Por otro lado, cualquier operación realizada sobre
    la temporada, se verá reflejada en sus capítulos y su eliminación supondrá la eliminación de estas.

- Clase Chapter

    - Identificador 

    Una temporada de una serie no puede contener dos capítulos con el mismo nombre. Por tanto se ha construido un identificador 
    compuesto formado por el atributo _title_ y el atributo _season_
    
    - Anotaciones JPA

    La relación con la entidad Season ya ha sido explicada, así como con la entidad User. 

- Clase Categorie

    - Identificador 

    Como el único dato relevante de la categoría hasta este momento es el precio y es la principal diferencia entre los distintos
    tipos de categoría, se ha elegido este atributo como identificador de la clase.

    - Anotaciones JPA

    La relación entre Categorie y Series se ha explicado anteriormente. En el lado de Categorie la anotación es _@OneToMany_
    indicando que una categoria se relaciona con muchas series. La anotación _mappedBy = "categorie"_ indica que la entidad Serie
    es la propietaria de la relación y que contendrá una columna de clave externa que apunte a su categoría. Además la anotación 
    _cascade = CascadeType.ALL_ indica que cualquier operación que se realice sobre una categoría, se verá reflejada en la serie 
    de dicha categoría.

    - Herencia

    El patrón aplicado en este caso ha sido el de _Single Table Inheritance_. De este modo, solo se tendrá la entidad Categorie y se 
    tendrá en la columna _pricePerChapter_ el precio por capítulo, determinado en las subclases. 

- Clase Bill

    - Identificador 

    Varias facturas en el sistema pueden tener el mismo coste, ser del mismo mes y año, encontrarse en un mismo estado, tener las 
    mismas Líneas de Factura y/o pertenecer al mismo usuario. Si bien es cierto que no debería ser posible que existan dos facturas
    para el mismo usuario y correspondientes al mismo mes y año (a menos que se consideren errores de facturación en el sistema), 
    he considerado mejor generar un atributo aleatorio.

    - Anotaciones JPA

    Como ya se ha dicho antes, un usuario tiene muchas facturas y una factura solo pertenece a un usuario.
    Por otro lado, una factura contiene muchas líneas de factura y he considerado no repetir líneas de factura, por lo que una línea
    de factura solo pertenece a una factura. También he considerado que el atributo bill en la entidad BillLine sea el que haga de 
    clave externa, de modo que en esta entidad se haga referencia a la factura a la que pertenece cada línea. Además de esto, si 
    se realiza cualquier modificación sobre la factura, sus líneas de factura sufren esta modificación y, en caso de eliminarse la
    factura, se eliminarían todas sus líneas de factura.

- Clase BillLine

    - Identificador 

    Como ya se ha dicho, varias líneas de factura pueden estar en la misma factura, pueden tener el mismo cargo y/o pueden ser sobre
    el mismo capítulo. Teóricamente, no pueden generarse dos facturas con la misma fecha de visualización, si en esta fecha se 
    precisan horas, minutos y/o segundos (a menos que se pueda abrir la cuenta en varios dispositivos). Como en el enunciado no se 
    especifica, he decidido no utilizar este dato como identificador y generar uno de manera aleatoria

    - Anotaciones JPA

    Ver explicación de la clase Bill.    









