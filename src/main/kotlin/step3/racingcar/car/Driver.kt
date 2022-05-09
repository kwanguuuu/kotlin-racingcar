package step3.racingcar.car

class Driver(
    private val car: Car
) {

    fun drive(moveNumber: Int) {
        if (isMovable(moveNumber)) {
            car.move()
        } else {
            car.stop()
        }
    }

    fun getDriveResult(): List<Position> {
        return car.getDrivingRecord()
    }

    private fun isMovable(moveNumber: Int): Boolean {
        return moveNumber >= DRIVE_DEFINE_NUMBER
    }

    companion object {
        private const val DRIVE_DEFINE_NUMBER = 4
    }
}
