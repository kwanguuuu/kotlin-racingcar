package racingcar.car

import racingcar.domain.strategy.MoveStrategy

class AlwaysTrueMoveStrategy : MoveStrategy {
    override fun isMovable(): Boolean {
        return true
    }
}
