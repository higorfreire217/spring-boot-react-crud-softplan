package com.softplan.backend;

//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import java.util.Collection;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

//@Entity
//class Beer {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//    private String name;
//
//    public Beer() {
//    }
//
//    public Beer(String name) {
//        this.name = name;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public String toString() {
//        return "Beer{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
//}
//
//@RepositoryRestResource
//interface BeerRepository extends JpaRepository<Beer, Long> {
//}
//
//@Component
//class BeerCommandLineRunner implements CommandLineRunner {
//
//    private final BeerRepository repository;
//
//    public BeerCommandLineRunner(BeerRepository repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public void run(String... strings) throws Exception {
//        // Top beers from https://www.beeradvocate.com/lists/us
//        Stream.of("Kentucky Brunch Brand Stout", "Marshmallow Handjee", "Barrel-Aged Abraxas",
//                "Hunahpu's Imperial Stout", "King Julius", "Heady Topper",
//                "Budweiser", "Coors Light", "PBR").forEach(name ->
//                repository.save(new Beer(name))
//        );
//        repository.findAll().forEach(System.out::println);
//    }
//}
//
//@RestController
//class BeerController {
//
//    private BeerRepository repository;
//
//    public BeerController(BeerRepository repository) {
//        this.repository = repository;
//    }
//
//    @GetMapping("/")
//    Collection<Beer> list() {
//        return repository.findAll();
//    }
//
//    @GetMapping("/good-beers")
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
//    public Collection<Beer> goodBeers() {
//
//        return repository.findAll().stream()
//                .filter(this::isGreat)
//                .collect(Collectors.toList());
//    }
//
//    private boolean isGreat(Beer beer) {
//        return !beer.getName().equals("Budweiser") &&
//                !beer.getName().equals("Coors Light") &&
//                !beer.getName().equals("PBR");
//    }
//}