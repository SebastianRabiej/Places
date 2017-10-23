import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
public class Generation {
  
  private List<Individual> individuals = new ArrayList();
  
  private Long averageFitness;
  
  private Integer smallestFitness;
  
  private Integer biggestFitness;

  private int selectionNumber = 5;
  
  public void addToGeneration(Individual individual) {
    individuals.add(individual);
  }
  
  public void calculateStatistics() {
    Integer biggestFit = Integer.MIN_VALUE;
    Integer smallestFit = Integer.MAX_VALUE;
    Integer sumFitness = 0;
    for (Individual individual : individuals) {
      sumFitness += individual.getFitness();

      if(individual.getFitness() > biggestFit) {
        biggestFit = individual.getFitness();
      }
      
      if(individual.getFitness() < smallestFit) {
        smallestFit = individual.getFitness();
      }
    }
    this.averageFitness = (long) sumFitness / (long)individuals.size();
    this.smallestFitness = smallestFit;
    this.biggestFitness = biggestFit;
  }

  public int sizeNecessaryToCreateNewGeneration() {
    return individuals.size()/2;
  }

  public List<Individual> getIndividualsForSelection() {
    List<Individual> fewFromGeneration = new ArrayList();
    for (int i = 0; i < selectionNumber ; i++) {
      fewFromGeneration.add(individuals.get((new Random()).nextInt(individuals.size())));
    }
    return fewFromGeneration;
  }
}
