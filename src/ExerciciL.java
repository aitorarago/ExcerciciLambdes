import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ExerciciL {

    public static void main(String[] args) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyy");
        Persona p1 = new Persona("Arya", Persona.Genere.DONA, LocalDate.parse("25/12/2002", format));
        Persona p2 = new Persona("Tyrion", Persona.Genere.HOME, LocalDate.parse("12/10/1980", format));
        Persona p3 = new Persona("Cersei", Persona.Genere.DONA, LocalDate.parse("10/01/1984", format));
        Persona p4 = new Persona("Eddard", Persona.Genere.HOME, LocalDate.parse("24/04/1974", format));
        Persona p5 = new Persona("Sansa", Persona.Genere.DONA, LocalDate.parse("24/04/1992", format));
        Persona p6 = new Persona("Jaime", Persona.Genere.HOME, LocalDate.parse("24/04/1979", format));
        Persona p7 = new Persona("Khal", Persona.Genere.HOME, LocalDate.parse("10/08/1979", format));
        Persona p8 = new Persona("Daenerys", Persona.Genere.DONA, LocalDate.parse("12/11/1992", format));
        Persona p9 = new Persona("Davos", Persona.Genere.HOME, LocalDate.parse("12/11/1965", format));
        Persona p10 = new Persona("Jon Neu", Persona.Genere.HOME, LocalDate.parse("12/11/1986", format));
        Persona p11 = new Persona("Brienne", Persona.Genere.DONA, LocalDate.parse("12/11/1989", format));

        Persona[] lpers = {p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11};
        List<Persona> llista_persones;
        llista_persones = new ArrayList<>(Arrays.asList(lpers));
        Map<Integer, Integer> mapPersones = new HashMap<>();

        // 1 - Canviar a lambda
        System.out.println("\n1-2");
        llista_persones.sort((o1, o2) -> {
            if (o2.getNom().charAt(0) >= o1.getNom().charAt(0)) return 1;
            else return -1;
        });

        // 2 - Canviar a Lambda
        llista_persones.forEach(persona -> System.out.println(persona.getAge()));

        // 3 - Canvia a classe anònima
        System.out.println("\n3-4");
        //ordenació alfabètica inversa del nom
        llista_persones.sort((o1, o2) -> {
            if (o2.getNom().charAt(0) >= o1.getNom().charAt(0)) return 1;
            else return -1;
        });

        // 4 - Canvia per una crida al mètode per referència
        llista_persones.forEach(ExerciciL::imp);


        // 5 - Omplir map. Canviar per un forEach amb lambda

        llista_persones.forEach(persona -> {
            mapPersones.put(persona.getAge(), 1);
        });


        // 6 - Canvia per un recorregut forEach amb lambda
        System.out.println("\n5");
        mapPersones.entrySet().forEach(integerIntegerEntry -> System.out.println(integerIntegerEntry.getKey() + " : " + integerIntegerEntry.getValue()));




        /* 7 -
            Esbrina com s'utilitzen els mètodes de map següents
                map.putIfAbsent = posa un nou objecte dins del map, sempre que la key no estigui ya asignada.
                map.computeIfAbsent = comproba si l'element ja esta al map, si es el cas no el fica, sino si ho fa.
                map.computeIfPresent = comproba si l'element esta dins del map, si ho esta pot fer una modificació del contingut.
             per tal d'afegir correctament la feqüència d'edat de les persones.
             Si vols fes-ho primer sense els mètodes anomenats i sense lambdes
             i després amb els mètodes i amb lambdes
             Exemple de sortida:
                34 anys -> 1
                38 anys -> 1
                39 anys -> 2
                26 anys -> 2
                44 anys -> 1
                15 anys -> 1
         */
        System.out.println("\n7");
        Map<Integer, Integer> edadMap = new HashMap<>();
        llista_persones.forEach(persona -> edadMap.putIfAbsent(persona.getAge(), 0));
        llista_persones.forEach(persona -> edadMap.computeIfPresent(persona.getAge(),
                (key, val) -> val + 1));
        edadMap.forEach((k, v) -> System.out.println((k + " anys -> " + v)));

        // 8 - llistat de persones DONA amb lambda (stream)
        System.out.println("\n8\n");
        llista_persones.stream().filter(persona -> persona.getGenere()==(Persona.Genere.DONA)).forEach(System.out::println);

        // 9 - Llistat dels dos HOMES més joves (stream)

        System.out.println("\n9\n");
        llista_persones.stream()
                .filter(persona -> persona.getGenere()==(Persona.Genere.HOME)).sorted(Comparator.comparingInt(Persona::getAge)).limit(2).forEach(System.out::println);

        // 10- Esborrar (no filtrar o imprimir) del llistat les persones entre 30 i 40 anys (amb lambda)

        llista_persones.removeIf(persona ->  persona.getAge()<=40 & persona.getAge()>=30);

        // 11 - Persones que tinguin una 'a' al seu nom
        System.out.println("\n11 Amb una 'A'");

        llista_persones.stream().filter(persona -> persona.getNom().contains("a")).forEach(System.out::println);

        //12 - Llistat de les dates de naixament + dos dies
        System.out.println("\n12 - dates amb dos dies més");

        llista_persones.forEach(persona -> System.out.println(persona.getDataNaixament().plusDays(2)));

        //13 - Rejovenir dos anys a totes les persones
        System.out.println("\n13 - Rejovenir dos anys a totes les persones");


        llista_persones.stream()
                .map(persona -> {
                    persona.setDataNaixament(persona.getDataNaixament().withYear(persona.getDataNaixament().getYear()-2));return persona;
                })
                .forEach(System.out::println);

    }

    public static void imp(Persona p) {
        System.out.println(p.getNom() + ", tinc " + p.getAge() + ", i he nascut el ida " + p.getDataNaixament());
    }


}




