package uz.gita.m1nex.paynet.app.screen.home

import uz.gita.m1nex.paynet.R

enum class BottomNavItem(val label: Int, val icon: Int, val route: String) {
    Main(R.string.main, R.drawable.ic_home, "Main"),
    Transfer(R.string.transfer, R.drawable.transfer, "Transfer"),
    Payment(R.string.payment, R.drawable.wallet, "Payment"),
    History(R.string.history, R.drawable.clock, "History")
}