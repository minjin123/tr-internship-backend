package springbook.tr.FeNo.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;

@Service
public class NitricOxideCalculate {

	/**
	 *  상수: offset, 최소 diff, 최대 diff
	 *  최소 diff = (최소 raw값 - OFFSET) = 5919 - 6055.51 = -136.51
	 *  최대 diff = (최대 raw값 - OFFSET) = 6183 - 6055.51 = 127.49
	 *  따라서 diff의 범위는 264.00 (127.49 - (-136.51))
	 */
	private static final BigDecimal OFFSET = new BigDecimal("6055.51");
	private static final BigDecimal MIN_DIFF = new BigDecimal("-136.51");
	private static final BigDecimal MAX_DIFF = new BigDecimal("127.49");
	private static final BigDecimal RANGE = MAX_DIFF.subtract(MIN_DIFF); // 264.00
	private static final BigDecimal HUNDRED = new BigDecimal("100");



	/**
	 * raw 값에 대해 offset 보정 후, diff 값을 0 ~ 100 범위로 선형 스케일링하여 반환
	 */
	public BigDecimal calculateScaled(BigDecimal value) {
		// 1. raw 값에서 offset 빼기: diff = value - OFFSET
		BigDecimal diff = value.subtract(OFFSET);
		// 2. diff 값의 범위는 MIN_DIFF ~ MAX_DIFF
		//    선형 스케일링을 위해 (diff - MIN_DIFF) 즉, diff - (-136.51) = diff + 136.51
		BigDecimal adjusted = diff.subtract(MIN_DIFF);
		// 3. 스케일링: (adjusted / RANGE) * 100, 소수점 2자리 반올림
		BigDecimal fraction = adjusted.divide(RANGE, 10, RoundingMode.HALF_UP);
		BigDecimal scaled = fraction.multiply(HUNDRED);
		return scaled.setScale(2, RoundingMode.HALF_UP);
	}
}

