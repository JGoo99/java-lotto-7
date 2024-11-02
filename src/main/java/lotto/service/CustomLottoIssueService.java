package lotto.service;

import java.util.Arrays;
import java.util.List;
import lotto.Lotto;
import lotto.model.db.LottoRepository;
import lotto.exception.BusinessException;
import lotto.util.ConsoleInput;

public class CustomLottoIssueService implements LottoIssueService {

    protected final LottoRepository lottoRepository = LottoRepository.getInstance();

    @Override
    public List<Lotto> issue(int lottoCnt) {
        Lotto winningLotto = null;
        while (winningLotto == null) {
            winningLotto = getCustomLotto("\n로또 번호를 입력해 주세요.");
        }
        return List.of(lottoRepository.save(winningLotto));
    }

    protected Lotto getCustomLotto(String prompt) {
        List<Integer> numbers = null;
        try {
            String inputNums = ConsoleInput.getStringWithQuestion(prompt);
            numbers = parseLottoNums(inputNums);
            validateLottoNumRange(numbers);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return new Lotto(numbers);
    }

    protected void validateLottoNumRange(List<Integer> numbers) {
        numbers.forEach(this::validateLottoNumRange);
    }

    protected void validateLottoNumRange(int num) {
        if (num < 1 || num > 45) {
            throw new BusinessException("로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    private static List<Integer> parseLottoNums(String inputNums) {
        return Arrays.stream(inputNums.split(","))
                .mapToInt(Integer::parseInt).boxed().toList();
    }
}
