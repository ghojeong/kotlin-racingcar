package racingcar.domain

import racingcar.domain.strategy.NumberGenerator

private const val MINIMUM_TRIAL_COUNT = 1

class RacingGame(cars: Cars, trialCount: Int) {

    val cars: Cars
    var trialCount: Int
        private set

    init {
        this.cars = cars
        require(trialCount >= MINIMUM_TRIAL_COUNT) { "시도 횟수는 1 이상이어야 합니다" }
        this.trialCount = trialCount
    }

    fun play(numberGenerator: NumberGenerator) {
        check(!isEnd()) { "남은 시도 횟수가 없습니다" }
        cars.moveAll(numberGenerator)
        trialCount--
    }

    fun isEnd(): Boolean {
        return trialCount <= 0
    }

    fun getWinners(): List<Car> {
        return cars.getWinners()
    }

    companion object {
        fun initGame(carNames: List<String>, trialCount: Int): RacingGame {
            return RacingGame(Cars.from(carNames), trialCount)
        }
    }
}
