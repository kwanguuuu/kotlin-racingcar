package racingcar.car

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import racingcar.domain.Drivers
import racingcar.domain.MoveStrategy
import racingcar.domain.NameLengthLimitStrategy
import racingcar.exception.InvalidDriverNameException

class DriversTest {

    @Test
    fun `드라이버에 생성시 기본으로 Car 이름 전략이 설정된다`() {
        val validCarName = "valid"
        val invalidCarName = "invalid"
        assertThrows<InvalidDriverNameException> {
            Drivers(
                listOf(validCarName, invalidCarName),
                AlwaysTrueMoveStrategy(),
                NameLengthLimitStrategy()
            )
        }

        assertThrows<InvalidDriverNameException> {
            Drivers(
                listOf(invalidCarName),
                AlwaysTrueMoveStrategy(),
            )
        }
    }

    @Test
    fun `드라이버의 전략이 true면 차가 움직일 수 있다`() {
        // given
        val alwayMoveDriver = Drivers(listOf("car"), AlwaysTrueMoveStrategy())

        // when
        val moveCount = 100
        val expectedDistance = 100
        repeat(moveCount) {
            alwayMoveDriver.driveAll()
        }

        // then
        val moveResults = alwayMoveDriver.getMoveResults()
        val moveDistance = moveResults.result[0].moveDistance

        assertThat(moveDistance).isEqualTo(expectedDistance)
    }

    @Test
    fun `드라이버의 전략이 false면 차가 움직일 수 없다`() {
        // given
        val alwayMoveDriver = Drivers(
            listOf("car"),
            object : MoveStrategy {
                override fun isMovable(): Boolean {
                    return false
                }
            }
        )

        // when
        val moveCount = 100
        val expectedDistance = 0
        repeat(moveCount) {
            alwayMoveDriver.driveAll()
        }

        // then
        val moveResults = alwayMoveDriver.getMoveResults()
        val moveDistance = moveResults.result[0].moveDistance

        assertThat(moveDistance).isEqualTo(expectedDistance)
    }
}
