package uz.gita.m1nex.paynet.app.screen.update

import android.os.Parcelable
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.parcelize.Parcelize
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.m1nex.core.data.model.card.CardData
import uz.gita.m1nex.core.data.model.card.UpdateCardRequest
import uz.gita.m1nex.core.data.model.sign.SignUp
import uz.gita.m1nex.core.getColors
import uz.gita.m1nex.core.getGradient
import uz.gita.m1nex.core.hiltScreenModel
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.BackgroundLight
import uz.gita.m1nex.paynet.app.ui.theme.component.AppButton
import uz.gita.m1nex.paynet.app.ui.theme.component.ItemCard
import uz.gita.m1nex.paynet.app.ui.theme.textColorLight90
import uz.gita.m1nex.presenter.screenmodel.card.CardContract
import uz.gita.m1nex.presenter.screenmodel.signup.SignUpContract
import uz.gita.m1nex.presenter.screenmodel.update.CardUpdateContract

@Parcelize
class CardUpdateScreen(val card: CardData): Screen,Parcelable {
    @Composable
    override fun Content() {
        val screenModel: CardUpdateContract.Model = hiltScreenModel()
        CardUpdateScreenContent(uiState = screenModel.collectAsState(), onEventDispatcher = screenModel::onEventDispatcher, card = card)
    }
}

@Composable
fun CardUpdateScreenContent(
    uiState: State<CardUpdateContract.UiState>,
    onEventDispatcher: (CardUpdateContract.Intent)->Unit,
    card: CardData
){
    var cardColor by remember {
        mutableIntStateOf(card.themeType)
    }
    var name by remember {
        mutableStateOf(card.name.ifEmpty { "Personal" })
    }
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
                .wrapContentSize()
        ) {
            Image(painter = painterResource(id = R.drawable.ic_navigation_arrow_left_x24),
                contentDescription = null,
                modifier = Modifier
                    .padding()
                    .size(24.dp)
                    .clickable {
                        onEventDispatcher.invoke(CardUpdateContract.Intent.Back)
                    })

            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = card.name,
                color = textColorLight90,
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                fontSize = 22.sp
            )
        }


        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(top = 56.dp, bottom = 10.dp)
                .verticalScroll(enabled = true, state = scrollState)
        ) {
            ItemCard(
                modifier = Modifier.padding(horizontal = 16.dp),
                data = card,
                gradient = getGradient(card.themeType)
            ) {

            }
            Spacer(modifier = Modifier.size(24.dp))
            TextField(value = name, onValueChange = {
                if(it.length in 1..19){
                    name = it
                }
            })

            Spacer(modifier = Modifier.size(12.dp))

            LazyRow (
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)){
                items(8){
                    val isSelected = it == cardColor
                    val borderColor = if (isSelected) Color.Green else Color.Gray
                    val borderWidth = if (isSelected) 4.dp else 2.dp

                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clickable { cardColor = it }
                            .border(
                                width = borderWidth,
                                color = borderColor,
                                shape = CircleShape
                            )
                            .background(
                                brush = Brush.radialGradient(
                                    colors = getColors(it),
                                    center = Offset(0.5f, 0.5f),
                                    radius = 0.5f
                                ),
                                shape = CircleShape
                            )
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight(1f)
                    .fillMaxWidth(0.9f)
            ) {
                Column(
                    Modifier
                        .align(Alignment.BottomCenter)
                ) {
                    AppButton(
                        text = stringResource(id = R.string.continue_txt),
                        onClick = {
                            onEventDispatcher.invoke(
                                CardUpdateContract.Intent.CardUpdate(
                                    UpdateCardRequest(card.id.toLong(),
                                    name.replace(' ','#'),
                                    cardColor,
                                    true)
                                )
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                    )
                }
            }


        }

    }
}