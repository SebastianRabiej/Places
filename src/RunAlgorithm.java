import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RunAlgorithm {

  private static int generationSize = 1000;

  public static void main(String[] args) {
    Generation generation = createFirstGeneration();
    printStatistics(generation);
    int maximumNumberOfGeneratedPopulations = 15000;
    int i = 0;
    while(i < maximumNumberOfGeneratedPopulations && !individualHasGoodFitness(generation)) {
      generation = createNewGeneration(generation);
      if(i%100 == 0) {
        System.out.println("Iteration number = "+i);
        printStatistics(generation);
        System.out.println("--------------------");
      }
      i++;
    }
    printStatistics(generation);
    if(generation.getSmallestFitness().equals(0)) {
      printLowestFitness(generation);
    }else {
      System.out.println("algorytm siê nie powiód³");
    }
  }

  private static void printStatistics(Generation generation) {
    System.out.println("Smallest fitness= "+generation.getSmallestFitness());
    System.out.println("Biggest fitness= "+generation.getBiggestFitness());
    System.out.println("Avg fitness= "+generation.getAverageFitness());
  }

  private static void printLowestFitness(Generation generation) {
    for (Individual individual : generation.getIndividuals()) {
      if(individual.getFitness().equals(generation.getSmallestFitness())) {
        System.out.println(individual.toString());
      }
    }
  }

  private static boolean individualHasGoodFitness(Generation generation) {
    for (Individual individual : generation.getIndividuals()) {
      if(individual.getFitness().equals(0)) {
        return true;
      }
    }
    return false;
  }

  private static Generation createNewGeneration(Generation generation) {
    Selection selection = new TournamentSelection();
    Generation newGeneration = new Generation();
    for (int i = 0; i < generation.sizeNecessaryToCreateNewGeneration(); i++) {
      final Individual firstWinner = selection.retriveWinner(generation.getIndividualsForSelection());
      final Individual secondWinner = selection.retriveWinner(generation.getIndividualsForSelection());
      List<Individual> crossedIndividuals = crossIndividuals(firstWinner,secondWinner);
      mutate(firstWinner);
      mutate(secondWinner);
      addIndividualsToGeneration(newGeneration, crossedIndividuals);
      //mutacja
    }
    newGeneration.calculateStatistics();
    return newGeneration;
  }

  private static void mutate(Individual individual) {
    final double mutatePropability = 0.05;
    for (int i = 0; i < individual.getChromosomes().size(); i++) {
      float propability = new Random().nextFloat();
      if (propability<mutatePropability) {
        individual.changeChromosonToNotRepeatedValue(i);
      }
    }
  }

  private static void addIndividualsToGeneration(Generation newGeneration,
      List<Individual> crossedIndividuals) {
    for (Individual individual : crossedIndividuals) {
      newGeneration.addToGeneration(individual);
    }
  }

  private static List<Individual> crossIndividuals(Individual firstWinner, Individual secondWinner) {
    List <Individual> crossedIndividuals = new ArrayList<>();

    final float propability = new Random().nextFloat();
    final double crossPropability = 0.7;
    if(propability < crossPropability) {
      crossedIndividuals.add(firstWinner);
      crossedIndividuals.add(secondWinner);
      return crossedIndividuals;
    }
    
    List<Integer> firstChromosomes = new ArrayList(firstWinner.getChromosomes());
    List<Integer> secondChromosomes = new ArrayList(secondWinner.getChromosomes());
    int whereToCut=5;
    List<Integer> subList = new ArrayList(firstChromosomes.subList(0, whereToCut));
    List<Integer> subList2 = new ArrayList(firstChromosomes.subList(whereToCut, firstChromosomes.size()));
    List<Integer> subList3 = new ArrayList(secondChromosomes.subList(0, whereToCut));
    List<Integer> subList4 = new ArrayList(secondChromosomes.subList(whereToCut, secondChromosomes.size()));
    subList.addAll(subList4);
    subList3.addAll(subList2);
    crossedIndividuals.add(new Individual(subList));
    crossedIndividuals.add(new Individual(subList3));
    return crossedIndividuals;
  }

  private static Generation createFirstGeneration() {
    Generation newGeneration = new Generation();
    for(int i=0; i<generationSize; i++) {
      newGeneration.addToGeneration(new Individual());
    }
    newGeneration.calculateStatistics();
    return newGeneration;
  }
  
}
