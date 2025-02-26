package springbook.tr.FeNo.Service;

import org.springframework.stereotype.Service;

@Service
public class NitricOxideCalculate {

	/**
	 *  상수: offset, 최소 diff, 최대 diff
	 *  최소 diff = (최소 raw값 - OFFSET) = 5919 - 6055.51 = -136.51
	 *  최대 diff = (최대 raw값 - OFFSET) = 6183 - 6055.51 = 127.49
	 *  따라서 diff의 범위는 264.00 (127.49 - (-136.51))
	 */
	private static final double OFFSET = 6055.51;
	private static final double MIN_DIFF = -136.51;
	private static final double MAX_DIFF = 127.49;
	private static final double RANGE = MAX_DIFF - MIN_DIFF; // 264.00
	private static final double HUNDRED = 100.0;

	/**
	 * raw 값에 대해 offset 보정 후, diff 값을 0 ~ 100 범위로 선형 스케일링하여 반환
	 */
	public double calculateScaled(double value) {
		// 1. raw 값에서 offset 빼기: diff = value - OFFSET
		double diff = value - OFFSET;
		// 2. diff 값의 범위는 MIN_DIFF ~ MAX_DIFF
		// 선형 스케일링을 위해 (diff - MIN_DIFF) 즉, diff - (-136.51) = diff + 136.51
		double adjusted = diff - MIN_DIFF;
		// 3. 스케일링: (adjusted / RANGE) * 100, 소수점 2자리 반올림
		double fraction = adjusted / RANGE;
		double scaled = fraction * HUNDRED;
		return Math.round(scaled * 100.0) / 100.0; // 소수점 둘째 자리 반올림
	}
}


