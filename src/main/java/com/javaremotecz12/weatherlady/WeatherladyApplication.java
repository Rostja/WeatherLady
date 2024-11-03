package com.javaremotecz12.weatherlady;

import com.javaremotecz12.weatherlady.model.Location;
import com.javaremotecz12.weatherlady.model.WeatherData;
import com.javaremotecz12.weatherlady.service.WeatherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class WeatherladyApplication implements CommandLineRunner {

	private final Scanner scanner;
	private final WeatherService weatherService;

	public WeatherladyApplication(WeatherService weatherService) {
        this.scanner = new Scanner(System.in);
        this.weatherService = weatherService;
    }

	public static void main(String[] args) {
		SpringApplication.run(WeatherladyApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
		boolean running = true;
		while(running){
			displayMenu();
			int choice = getUserChoice();

			switch(choice){
				case 1:
					addLocation();
					break;
				case 2:
					getWeatherData();
					break;
				case 3:
					running = false;
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		}
		scanner.close();
	}

	private void displayMenu(){
		System.out.println("\n ======Weather Lady Application======");
		System.out.println("1. Add new location");
		System.out.println("2. Get weather data");
		System.out.println("3. Exit");
		System.out.println("Enter your choice: ");
	}

	private int getUserChoice(){
		try{
			return Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e){
			return -1;
		}
	}

	private void addLocation(){
		System.out.println("\n=======Add New Location=======");

		Location location = new Location();

		System.out.println("Enter latitude (-90 to 90): ");
		location.setLatitude(Double.parseDouble(scanner.nextLine()));

		System.out.println("Enter longitude (-90 to 90): ");
		location.setLongitude(Double.parseDouble(scanner.nextLine()));

		System.out.println("Enter country: ");
		location.setCountry(scanner.nextLine());

		System.out.println("Enter region (optional,press enter to skip): ");
		String region = scanner.nextLine();
		if (!region.isEmpty()){
			location.setRegion(region);
		}

		System.out.println("Enter city name: ");
		location.setCity(scanner.nextLine());

        try {
            Location savedLocation = weatherService.addLocation(location);
            System.out.println("Location saved successfully! ID: " + savedLocation.getId());
        } catch (Exception e) {
            System.out.println("Error saving location: " + e.getMessage());
        }
	}
    private void getWeatherData(){
        System.out.println("\n======Get Weather Data======");
        System.out.println("Enter city name: ");
        String city = scanner.nextLine();

        try{
            WeatherData weatherData = weatherService.getWeatherData(city);
            System.out.println("\n Weather for " + city);
            System.out.println("Temperature: " + weatherData.getTemperature() + "°C");
            System.out.println("Humidity: " + weatherData.getHumidity() + "%");
            System.out.println("Pressure: " + weatherData.getPressure() + " hPa");
            System.out.println("Wind Speed: " + weatherData.getWindSpeed() + " m/s");
            System.out.println("Wind Direction: " + weatherData.getWindDirection());
        } catch (Exception e) {
            System.out.println("Error getting weather data: " + e.getMessage());
        }
    }
}
