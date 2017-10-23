import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class test {

  @Test
  public void test() {
    final Individual individual = new Individual(Arrays.asList(6, 3, 1, 9, 2, 10, 8, 11, 7));
    System.out.println(individual.getFitness());
  }
}
