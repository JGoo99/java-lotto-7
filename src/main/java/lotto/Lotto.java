package lotto;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream().sorted().toList();
    }

    private void validate(List<Integer> numbers) {
        if (numbers.stream().distinct().count() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복되지 않는 번호 6개여야 합니다.");
        }
    }

    public boolean contains(int num) {
        return numbers.contains(num);
    }

    public int getCnt(Lotto lotto) {
        return (int) numbers.stream().filter(lotto::contains).count();
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
