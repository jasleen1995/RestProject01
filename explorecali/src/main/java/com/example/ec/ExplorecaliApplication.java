package com.example.ec;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import com.example.ec.demos.Difficulty;
import com.example.ec.demos.Region;
import com.example.ec.service.TourPackageService;
import com.example.ec.service.TourService;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

//@EntityScan(basePackages = "com.example.ec")
//@ComponentScan(basePackages = {"com.example.ec.service"})
@SpringBootApplication
public class ExplorecaliApplication implements CommandLineRunner{

	/*
	 * @Value("${ec.importfile}") private String importFile;
	 */
	
	@Autowired
	private TourPackageService tourPackageService;
	
	@Autowired
	private TourService tourService;
	
	public static void main(String[] args) {
		SpringApplication.run(ExplorecaliApplication.class, args);
	}
	
	/*
	 * private void loadToursAtStartup() throws IOException{
	 * 
	 * }
	 */

	@Override
	public void run(String... args) throws Exception {
		//Making body of loadToursAtStartup as run body
		//Create the Tour packages
		createTourPackages();
		long numOfPackages = tourPackageService.total();
		
		//Load the tours from an external Json File
		createTours("ExploreCalifornia.json");
		long numOfTours = tourService.total();

	}
	
	/*
	 * Initialize all the known tour packages
	 * 
	 */
	
	private void createTourPackages() {
		tourPackageService.createTourPackage("BC", "Backpack Cal");
		tourPackageService.createTourPackage("CC", "California Calm");
		tourPackageService.createTourPackage("CH", "California Hot springs");
        tourPackageService.createTourPackage("CY", "Cycle California");
        tourPackageService.createTourPackage("DS", "From Desert to Sea");
        tourPackageService.createTourPackage("KC", "Kids California");
        tourPackageService.createTourPackage("NW", "Nature Watch");
        tourPackageService.createTourPackage("SC", "Snowboard Cali");
        tourPackageService.createTourPackage("TC", "Taste of California");
	}
	
	/*
	 * 
	 * Create tour entities from an external file
	 */
	
	//private void createTours(String fileToImport) throws IOException{
		/*
		 * TourFromFile.read(fileToImport).forEach(importedTour ->
		 * tourService.createTour( importedTour.getTitle(),
		 * importedTour.getDescription(), importedTour.getBlurb(),
		 * importedTour.getPrice(), importedTour.getLength(), importedTour.getBullets(),
		 * importedTour.getKeywords(), importedTour.getPackageType(),
		 * importedTour.getDifficulty(), importedTour.getRegion()));
		 */
	//}
	private void createTours(String fileToImport) throws IOException {
        TourFromFile.read(fileToImport).forEach(tourFromFile ->
                        tourService.createTour(tourFromFile.getTitle(),
                                tourFromFile.getPackageName(), tourFromFile.getDetails())
        );
    }


	/*
	 * Helper class to import ExploreCalifornia.json
	 */
	
	/*
	 * private static class TourFromFile{ //fields private String packageType,
	 * title, description, blurb, price, length, bullets, keywords, difficulty,
	 * region;
	 * 
	 * //reader static List<TourFromFile> read(String fileToImport) throws
	 * IOException{ return new ObjectMapper().setVisibility(PropertyAccessor.FIELD,
	 * Visibility.ANY) .readValue(new FileInputStream(fileToImport) , new
	 * TypeReference<List<TourFromFile>>() {}); }
	 * 
	 * public Map<String, String> getDetails() { // TODO Auto-generated method stub
	 * return null; }
	 * 
	 * public String getPackageName() { // TODO Auto-generated method stub return
	 * null; }
	 * 
	 * protected TourFromFile(){}
	 * 
	 * String getPackageType() { return packageType; }
	 * 
	 * String getTitle() { return title; }
	 * 
	 * String getDescription() { return description; }
	 * 
	 * String getBlurb() { return blurb; }
	 * 
	 * Integer getPrice() { return Integer.parseInt(price); }
	 * 
	 * String getLength() { return length; }
	 * 
	 * String getBullets() { return bullets; }
	 * 
	 * String getKeywords() { return keywords; }
	 * 
	 * Difficulty getDifficulty() { return Difficulty.valueOf(difficulty); }
	 * 
	 * Region getRegion() { return Region.findByLabel(region); } }
	 */

	/**
     * Helper class to import ExploreCalifornia.json for a MongoDb Document.
     * Only interested in the title and package name, the remaining fields
     * are a collection of key-value pairs
     *
     */
    private static class TourFromFile {
        //fields
        String title;
        String packageName;
        Map<String, String> details;

        TourFromFile(Map<String, String> record) {
            this.title =  record.get("title");
            this.packageName = record.get("packageType");
            this.details = record;
            this.details.remove("packageType");
            this.details.remove("title");
        }
        //reader
        static List<TourFromFile> read(String fileToImport) throws IOException {
            List<Map<String, String>> records = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, Visibility.ANY).
                    readValue(new FileInputStream(fileToImport),
                            new TypeReference<List<Map<String, String>>>() {});
            return records.stream().map(TourFromFile::new)
                    .collect(Collectors.toList());
        }

        String getTitle() {
            return title;
        }

        String getPackageName() {
            return packageName;
        }

        Map<String, String> getDetails() {
            return details;
        }
    }
	
}
