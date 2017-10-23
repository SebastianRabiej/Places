import java.util.List;

public class TournamentSelection implements Selection {

  @Override
  public Individual retriveWinner(List<Individual> individualsForSelection) {
    Individual theBestIndividual = individualsForSelection.get(0);
    for (Individual individual : individualsForSelection) {
      if(individual.getFitness() < theBestIndividual.getFitness()) {
        theBestIndividual = individual;
      }
    }
    return theBestIndividual;
  }

}
