import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Getter
public class Individual {

  private List<Integer> chromosomes;
  
  private Integer fitness;
  
  public Individual() {
    chromosomes = new ArrayList<>();
    for (int i = 0; i < 9; i++) {
      chromosomes.add(new Random().nextInt(11)+1);
    }
    this.fitness = calculateFitness();
  }
  
  public Individual(List<Integer> chromosones) {
    this.chromosomes = chromosones;
    this.fitness = calculateFitness();
  }
  
  public Integer calculateFitness() {
    Integer fit=0;
    for (int i = 0; i < chromosomes.size(); i++) {
      final List<Integer> list = FitnessCondition.conditions.get(i);
      if(!list.contains(chromosomes.get(i))) {
        fit+=2*(11-FitnessCondition.conditions.get(i).size());
      }
    }
    Set<Integer> chromosomesWithoutRepeat = new HashSet<Integer>(chromosomes);
    fit += ((chromosomes.size() - chromosomesWithoutRepeat.size())*3);
    return fit;
  }

  public void changeChromosonToNotRepeatedValue(int i) {
    Integer index =0 ;
    Integer nonRepetedValue=1;
    while(index  < chromosomes.size() ) {
      if(nonRepetedValue.equals(chromosomes.get(index))){
        index = 0;
        nonRepetedValue++;
      }
      index++;
    }
    chromosomes.set(i, nonRepetedValue);
    this.fitness = calculateFitness();
  }
  
  @Override
  public String toString() {
    return chromosomes.toString();
  }

}
