# Practiac Desarrollo Empresas

# Explicación del Modelo de Dominio

Clases:
- Bill - BillLine - User (MonthlyUser y NormalUser) - Series - Categorie (con sus descendientes) - Season - Chapter


Relaciones:
- Series ---- Categorie
Aunque una Serie tiene solo una Categoría en un momento dentro del sistema, dicha Serie puede cambiar de categoría a lo largo del tiempo
(cuando una serie es nueva normalmente pertenece a una categoría más cara), por lo que en el histórico, tiene múltiples categorías. Por esta
misma explación, una misma categoría puede "contener" muchas series (al menos 1, ya que no tendría sentido tener una Categoría en el sistema
de la que no forme parte ninguna Serie). 
Así mismo, Categoría tiene una subclase por cada tipo de categoría en el sistema. Categoría tiene de atributo el precio, que es el mismo para
todas las series de la categoría, por eso es a nivel de clase.

- Series ---- Season ---- Categorie
Estas 3 clases tienen relación de composición entre sí, de manera que si una serie deja de existir en el sistema, también dejarían de existir
sus temporadas y sus capítulos. Aunque no aparece en la descripción del sistema específicamente, he decidido almacenar en el capítulo la 
fecha de emisión. De esta forma, se puede obtener fácilmente la fecha de emisión de la temporada a la que pertenece y también de la propia serie
(buscando el primer capítulo).

- Bill ---- BillLine ---- Chapter
En la factura se guarda la fecha de emisión (el mes y el año para ser más específico) y el estado de la factura. He dividido dicho estado en:
en progreso, pendiente y pagada. Cuando se crea una factura, su estado inicial es en progreso. Cuando finaliza el mes, pasa a estar a estado
pendiente hasta que se paga.
La factura tiene varias líneas y esto se representa con la clase BillLine. Esta clase BillLine recoge información del capítulo visto y de la 
temporada y serie a la que pertenece. Si la factura se elimina del sistema, también se elimina la línea de factura. Un capítulo puede encontrarse 
en una línea de factura o en muchas, o puede no haber sido visto por nadie.

- Bill ---- User
Un usuario puede tener 0 o muchas facturas en el sistema. Aunque solo tenga 1 factura (o más, no se especifica en el enunciado) pendiente, las demás
facturas que ya ha pagado deben estar guardadas en el sistema. Dentro del sistema hay diferentes tipos de usuarios. El usuario mensual tiene
una tasa fija al mes. Aunque esto podría describirse también con por ejemplo un booleano y alguna otra variable más, creo que esta solución es más
elegante y más escalable.

- User ---- Series / User ---- Chapter
El usuario tiene 3 tipos de listas con series. Una lista de series pendientes, otra de empezadas y otra de finalizadas. Esto se modela con relaciones
a diferencia de el estado de la factura, ya que la factura pertenece al usuario pero la serie es independiente del mismo, y si se modelase con un estado,
significaría que esa serie tiene ese estado en todo el sistema. 
El usuario tiene a su vez una serie de capítulos vistos, para tener en cuenta cuando se ha acabado una temporada o una serie y marcarlo como tal.
Un capítulo puede no haber sido visto por ningún usuario o por varios.



