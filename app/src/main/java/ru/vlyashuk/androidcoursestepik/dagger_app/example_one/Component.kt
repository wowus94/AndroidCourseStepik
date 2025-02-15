package ru.vlyashuk.androidcoursestepik.dagger_app.example_one

class Component {

    private fun getComputer(): Computer {
        val monitor = Monitor()
        val mouse = Mouse()
        val keyboard = Keyboard()
        val computerTower = ComputerTower(
            Memory(),
            Storage(),
            Processor()
        )
        return Computer(monitor, mouse, keyboard, computerTower)
    }

    fun inject(activity: Activity) {
     //   activity.keyboard = Keyboard()
    }
}