package uz.gita.m1nex.paynet.app.screen.home.tab.transfer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import uz.gita.m1nex.paynet.R

object TransferTab: Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.transfer)
            val icon = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.transfer))

            return remember(title, icon) {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {

    }
}