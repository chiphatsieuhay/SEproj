package SimulatedAnnealing;

public class SimulatedAnnealing {
    
    public static void main(String[] args) {
         City city = new City("Paris",60, 200);
        TourManager.addCity(city);
        City city2 = new City("Lyon",180, 200);
        TourManager.addCity(city2);
        City city3 = new City("La Rochelle",80, 180);
        TourManager.addCity(city3);
        City city4 = new City("Bordeaux",140, 180);
        TourManager.addCity(city4);
        City city5 = new City("Lenz",20, 160);
        TourManager.addCity(city5);
        City city6 = new City("Nice",100, 160);
        TourManager.addCity(city6);
        City city7 = new City("Lille",200, 160);
        TourManager.addCity(city7);
        City city8 = new City("Rennes",140, 140);
        TourManager.addCity(city8);
        City city9 = new City("Brest",40, 120);
        TourManager.addCity(city9);
        City city10 = new City("Toulon",100, 120);
        TourManager.addCity(city10);        
        City city11 = new City("Nancy",180, 100);
        TourManager.addCity(city11);
        City city12 = new City("Calais",60, 80);
        TourManager.addCity(city12);
        
        double temp = 100000;

        double coolingRate = 0.003;

        Tour currentSolution = new Tour();
        currentSolution.generateIndividual();
        
        System.out.println("Total distance of initial solution: " + currentSolution.getTotalDistance());
        System.out.println("Tour: " + currentSolution);

        Tour best = new Tour(currentSolution.getTour());
        
        while (temp > 1) {
            Tour newSolution = new Tour(currentSolution.getTour());

            int tourPos1 = Utility.randomInt(0 , newSolution.tourSize());
            int tourPos2 = Utility.randomInt(0 , newSolution.tourSize());
            
    		while(tourPos1 == tourPos2) {tourPos2 = Utility.randomInt(0 , newSolution.tourSize());}

            City citySwap1 = newSolution.getCity(tourPos1);
            City citySwap2 = newSolution.getCity(tourPos2);

            newSolution.setCity(tourPos2, citySwap1);
            newSolution.setCity(tourPos1, citySwap2);
            
            int currentDistance   = currentSolution.getTotalDistance();
            int neighbourDistance = newSolution.getTotalDistance();

            double rand = Utility.randomDouble();
            if (Utility.acceptanceProbability(currentDistance, neighbourDistance, temp) > rand) {
                currentSolution = new Tour(newSolution.getTour());
            }

            if (currentSolution.getTotalDistance() < best.getTotalDistance()) {
                best = new Tour(currentSolution.getTour());
            }
            
            temp *= 1 - coolingRate;
        }

        System.out.println("Final solution distance: " + best.getTotalDistance());
        System.out.println("Tour: " + best);
    }
}
