package es.unican.empresariales.julio.polaflix;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import es.unican.empresariales.julio.polaflix.entities.Categorie;
import es.unican.empresariales.julio.polaflix.entities.Chapter;
import es.unican.empresariales.julio.polaflix.entities.MonthlyUser;
import es.unican.empresariales.julio.polaflix.entities.NormalUser;
import es.unican.empresariales.julio.polaflix.entities.Season;
import es.unican.empresariales.julio.polaflix.entities.Series;
import es.unican.empresariales.julio.polaflix.entities.User;
import es.unican.empresariales.julio.polaflix.repositories.CategorieRepository;
import es.unican.empresariales.julio.polaflix.repositories.SeriesRepository;
import es.unican.empresariales.julio.polaflix.repositories.UserRepository;


@Component
public class AppFeeder implements CommandLineRunner {

	@Autowired
	protected CategorieRepository cr;
	@Autowired
	protected SeriesRepository seriesR;
	@Autowired
	protected UserRepository ur;

	//Chapter chapter111;

	
	@Override
	public void run(String... args) throws Exception {
		feedDatabase();
		System.out.println("Application feeded");
		testDatabase();
		
	}

	private void feedDatabase() {

		//Categorías en la aplicación
		Categorie goldCategorie = new Categorie("Gold", 1.5);
		Categorie silverCategorie = new Categorie("Silver", 0.75);
		Categorie standarCategorie = new Categorie("Standar", 0.5);
		cr.save(goldCategorie);
		cr.save(silverCategorie);
		cr.save(standarCategorie);

		Set<String> creators1 = new HashSet<String>();
		Set<String> creators2 = new HashSet<String>();
		Set<String> creators3 = new HashSet<String>();
		Set<String> actors1 = new HashSet<String>();
		Set<String> actors2 = new HashSet<String>();
		Set<String> actors3 = new HashSet<String>();
		List<Season> seasonsSeries1 = new ArrayList<Season>();
		List<Season> seasonsSeries2 = new ArrayList<Season>();
		List<Season> seasonsSeries3 = new ArrayList<Season>();
		List<Chapter> chaptersSeason1Series1 = new ArrayList<Chapter>();
		List<Chapter> chaptersSeason2Series1 = new ArrayList<Chapter>();
		List<Chapter> chaptersSeason1Series2 = new ArrayList<Chapter>();		
		List<Chapter> chaptersSeason2Series2 = new ArrayList<Chapter>();		
		List<Chapter> chaptersSeason1Series3 = new ArrayList<Chapter>();		
		List<Chapter> chaptersSeason2Series3 = new ArrayList<Chapter>();		

		String synopsis1 = "En un mundo fantástico y en un contexto medieval varias familias, relativas a la nobleza, se disputan el poder para dominar el territorio ficticio de Poniente (Westeros)" +
		"y tomar el control de los Siete Reinos desde el Trono de Hierro...";
		creators1.add("George R.R. Martin");
		actors1.add("Emilia Clarke");	
		actors1.add("Pedro Pascal");	
		actors1.add("Bella Ramsey");	
		actors1.add("Sophie Turner");	
		actors1.add("Maisie Williams");	
		actors1.add("KitHarington");	

		String synopsis2 = "¿Qué harías si te enteraras de que te quedan pocos meses de vida, estuvieras a punto de tener un hijo y tuvieras deudas sin solventar?"+
		"Ante esta situación, Walter White, un profesor de química...";
		creators2.add("Vince gilligan");
		actors2.add("Bryan Cranston");
		actors2.add("Aaron Paul");
		actors2.add("Anna Gunn" );
		actors2.add("Dean Norris");
		actors2.add("RJ Mitte");
		actors2.add("Bob Odenkirk");

		String synopsis3 = "La historia de The Walking Dead nos traslada a un escenario post-pandémico en el que un virus ha acabado con la práctica totalidad de la población mundial convirtiéndolos" +
		"en zombis.";
		creators3.add("Robert Kirkman");
		actors3.add("Nomran Reedus");
		actors3.add("Andrew Lincoln");
		actors3.add("Melissa McBride");
		actors3.add("Laruen Cohan");
		actors3.add("Jeffrey Dean Morgan");
		actors3.add("Danai Gurira");


		Series series1 = new Series("Juego de tronos", synopsis1, creators1, actors1, goldCategorie);
		Season season1Series1 = new Season(1, series1);
		Season season2Series1 = new Season(2, series1);
		//Season season12 = new Season(2, series1);
		Chapter chapter1Season1Series1 = new Chapter("Se acerca el invierno", "Los Starks de Invernalia acogen la visita de la familia real, del rey Robert Baratheon. Paralelamente, al otro lado del mar, el príncipe" + 
		"exiliado Targaryen traza una alianza para recuperar el Trono de Hierro.", 1, Duration.ofMinutes(54).plusSeconds(27), "link1", LocalDate.of(2011, 4,17), season1Series1);
		Chapter chapter2Season1Series1 = new Chapter("El camino real", "Ned parte hacia Desembarco del Rey, tras aceptar la oferta como Mano del Rey, acompañado de sus hijas Sansa y Arya. También le acompaña Jon Nieve," +
		"su hijo bastardo, quien se dirige al Muro para unirse a la Guardia de la Noche.", 2, Duration.ofMinutes(50).plusSeconds(45), "link2", LocalDate.of(2011, 4,24), season1Series1);
		Chapter chapter1Season2Series1 = new Chapter("El norte recuerda", "En los siete reinos se bate una furiosa guerra civil, Stannis Baratheon y Renly no reconocen a Jeoffrey como rey y ambos aspiran al Trono de Hierro." + 
		"Mientras, el Norte se ha declarado independiente y Tyrion es nombrado Mano del Rey.", 1, Duration.ofMinutes(55).plusSeconds(55), "link3", LocalDate.of(2012, 4,1), season2Series1);
		
		chaptersSeason1Series1.add(chapter1Season1Series1);
		chaptersSeason1Series1.add(chapter2Season1Series1);
		chaptersSeason2Series1.add(chapter1Season2Series1);
		season1Series1.setChapters(chaptersSeason1Series1);
		season2Series1.setChapters(chaptersSeason2Series1);
		seasonsSeries1.add(season1Series1);
		seasonsSeries1.add(season2Series1);
		series1.setSeasons(seasonsSeries1);
	
		Series series2 = new Series("Breaking bad", synopsis2, creators2, actors2, silverCategorie);
		Season season1Series2 = new Season(1, series2);
		Season season2Series2 = new Season(2, series2);
		//Season season12 = new Season(2, series1);
		Chapter chapter1Season1Series2 = new Chapter("Piloto", "A Walter White, profesor de química de instituto, le diagnostican un cáncer de pulmón en fase terminal. El hombre se obsesiona con "+
		"la idea de asegurar un futuro para su familia y hará todo lo que esté en sus manos para conseguirlo.", 1, Duration.ofMinutes(54).plusSeconds(27), "link4", LocalDate.of(2008, 4,17), season1Series2);
		Chapter chapter2Season1Series2 = new Chapter("El gato está en la caja...", "Después del desastre ocurrido en el desierto, Walter y Jesse deben decidir que hacer con los cuerpos de Emilio y Krazy 8. Mientras tanto, Skyler" +
		"empieza a sospechar del extraño comportamiento de su marido.", 2, Duration.ofMinutes(50).plusSeconds(45), "link5", LocalDate.of(2008, 4,24), season1Series2);
		Chapter chapter1Season2Series2 = new Chapter("Mejor llamar a Saul", "Walt y Jesse podrían ser delatados a la policía en cualquier momento, así que contratan a un abogado de medio pelo que se" + 
		"anuncia en televisión para llevar el tema: se llama Saul Goodman y tiene soluciones para todo.", 1, Duration.ofMinutes(55).plusSeconds(55), "link6", LocalDate.of(2009, 4,1), season2Series2);
		
		chaptersSeason1Series2.add(chapter1Season1Series2);
		chaptersSeason1Series2.add(chapter2Season1Series2);
		chaptersSeason2Series2.add(chapter1Season2Series2);
		season1Series2.setChapters(chaptersSeason1Series2);
		season2Series2.setChapters(chaptersSeason2Series2);
		seasonsSeries2.add(season1Series2);
		seasonsSeries2.add(season2Series2);
		series2.setSeasons(seasonsSeries2);


		Series series3 = new Series("Walking Dead", synopsis3, creators3, actors3, standarCategorie);
		Season season1Series3 = new Season(1, series3);
		Season season2Series3 = new Season(2, series3);
		//Season season12 = new Season(2, series1);
		Chapter chapter1Season1Series3 = new Chapter("Días pasados", "Rick Grimes, policía de una pequeña localidad de Georgia, despierta de un coma y repentinamente se encuentra en un mundo apocalíptico. Desorientado," +
		"en busca de su familia y una explicación a lo acontecido, Rick ha de ajustarse al nuevo orden.", 1, Duration.ofMinutes(54).plusSeconds(27), "link7", LocalDate.of(2008, 4,17), season1Series3);
		Chapter chapter2Season1Series3 = new Chapter("Agallas", "Rick llega a caballo a Atlanta donde encuentra una ciudad desolada. Tras escapar de una situación de peligro, Rick se une a un" +
		"grupo de personas en unos grandes almacenes donde le esperará un enemigo más peligroso que los zombies.", 2, Duration.ofMinutes(50).plusSeconds(45), "link8", LocalDate.of(2008, 4,24), season1Series3);
		Chapter chapter1Season2Series3 = new Chapter("Lo que está por venir", "La caravana del grupo de sobrevivientes liderada por Rick Grimes queda atascada en medio de una autopista. Sorprendidos por un" + 
		"ataque de zombies, el grupo pierde a Sophia y hacen lo posible por encontrarla antes de que sea demasiado tarde.", 1, Duration.ofMinutes(55).plusSeconds(55), "link9", LocalDate.of(2009, 4,1), season2Series3);
		
		chaptersSeason1Series3.add(chapter1Season1Series3);
		chaptersSeason1Series3.add(chapter2Season1Series3);
		chaptersSeason2Series3.add(chapter1Season2Series3);
		season1Series3.setChapters(chaptersSeason1Series3);
		season2Series3.setChapters(chaptersSeason2Series3);
		seasonsSeries3.add(season1Series3);
		seasonsSeries3.add(season2Series3);
		series3.setSeasons(seasonsSeries3);

		User u1 = new MonthlyUser("Julio", "1234", "ES66 2100 0418 4012 3456 7891");
		User u2 = new NormalUser("Marta", "5678", "ES66 2100 0418 4012 3456 7892");

		seriesR.save(series1);
		seriesR.save(series2);
		seriesR.save(series3);

		u1.addSeriesToPendingSeries(series1);
		u1.addSeriesToPendingSeries(series3);
		u2.addSeriesToPendingSeries(series2);
		u1.addChapterWatched(chapter1Season1Series1);
		u1.addChapterWatched(chapter2Season1Series1);
		u1.addChapterWatched(chapter1Season2Series1);
		u2.addChapterWatched(chapter1Season1Series2);
		u2.addChapterWatched(chapter2Season1Series2);
		u2.addChapterWatched(chapter1Season1Series3);

		ur.save(u1);
		ur.save(u2);

	}

	private void testDatabase() {
		
		//User user1 = ur.findById((long) 1).get();
		//User user2 = ur.findById((long) 2).get();
		//System.out.println(user1.getBills().size());
		//System.out.println("Factura usuario 1: " + user1.getBillCurrentMonth().calculateBill());
		//System.out.println("Factura usuario 2: " + user2.getBillCurrentMonth().calculateBill());
		

	}
}

