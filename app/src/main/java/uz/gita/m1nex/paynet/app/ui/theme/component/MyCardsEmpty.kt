package uz.gita.m1nex.paynet.app.ui.theme.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.gita.m1nex.core.angledGradientBackground
import uz.gita.m1nex.core.data.model.card.CardData
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.ShadowColorCard
import uz.gita.m1nex.paynet.app.ui.theme.cardColor
import uz.gita.m1nex.paynet.app.ui.theme.mainBgLight
import uz.gita.m1nex.paynet.app.ui.theme.primaryColor

@Composable
fun MyCardsEmpty(
    modifier: Modifier = Modifier, onClickAddCard: () -> Unit
) {
    Column(
        modifier
            .fillMaxWidth()
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = ShadowColorCard,
            )
            .background(cardColor)
            .padding(8.dp),
    ) {
        TextBoldBlack(
            modifier = Modifier.padding(2.dp),
            text = stringResource(id = R.string.mine_card),
            style = MaterialTheme.typography.bodyMedium
        )

        Column(
            Modifier
                .padding(1.dp)
                .weight(1f)
                .fillMaxWidth()
                .shadow(
                    elevation = 1.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = ShadowColorCard,
                )
                .background(cardColor)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onClickAddCard
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_add_card),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
            )

            TextBoldBlack(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(id = R.string.add_card),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun MyCardsOneCard(
    modifier: Modifier = Modifier,
    onClickAddCard: () -> Unit,
    onClickCard: (CardData) -> Unit,
    card: CardData
) {
    Column(
        modifier = modifier
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = ShadowColorCard,
            )
            .background(cardColor)
            .padding(8.dp)
    ) {
        TextBoldBlack(modifier = Modifier.padding(2.dp),
            text = stringResource(id = R.string.cards),
            style = MaterialTheme.typography.bodySmall
        )
        Box(
            modifier = Modifier
                .padding(top = 4.dp)
                .height(200.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onClickAddCard
                )
        ) {
            Column(
                modifier = Modifier
                    .height(120.dp)
                    .aspectRatio(ratio = 1.5857725f)
                    .padding(2.dp)
                    .shadow(
                        elevation = 1.dp,
                        shape = RoundedCornerShape(16.dp),
                        ambientColor = ShadowColorCard,
                    )
                    .background(mainBgLight)

            ) {
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxWidth(), contentAlignment = Alignment.Center
                ) {
                    Text(text = stringResource(id = R.string.add_card),
                        style = MaterialTheme.typography.bodySmall)
                }
                Spacer(modifier = Modifier.height(40.dp))
            }

            ItemCard(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height(120.dp)
                    .aspectRatio(ratio = 1.5857725f)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) {
                        onClickCard.invoke(card)
                    }, cardData = card
            )
        }
    }
}


@Composable
fun MyCardsTwoCards(
    modifier: Modifier = Modifier,
    onClickAddCard: () -> Unit,
    onClickFrontCard: (data: CardData) -> Unit,
    onClickBackCard: (data: CardData) -> Unit,
    frontCard: CardData,
    backCard: CardData,
) {
    Column(
        modifier = modifier
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = ShadowColorCard,
            )
            .background(cardColor)
            .padding(12.dp)

    ) {
        Box(Modifier.fillMaxWidth()) {
            TextBoldBlack(
                text = stringResource(id = R.string.cards),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.CenterStart)
            )
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .clip(CircleShape)
                    .background(primaryColor)
                    .align(Alignment.CenterEnd)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onClickAddCard
                    )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = null,
                    modifier = Modifier.padding(4.dp),
                    tint = Color.White,
                )
            }
        }

        Box(
            modifier = Modifier
                .padding(top = 12.dp)
                .height(160.dp)

        ) {
            ItemCard(
                modifier = Modifier
                    .height(96.dp)
                    .fillMaxWidth()
                    //.aspectRatio(ratio = 1.5857725f)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) {
                        onClickBackCard.invoke(backCard)
                    }, cardData = backCard
            )

            ItemCard(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height(96.dp)
                    .fillMaxWidth()
                    //.aspectRatio(ratio = 1.5857725f)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) {
                        onClickFrontCard.invoke(frontCard)
                    }, cardData = frontCard
            )
        }
    }
}

@Composable
fun MyCardsMoreCards(
    modifier: Modifier = Modifier,
    onClickAddCard: () -> Unit,
    onClickFrontCard: (data: CardData) -> Unit,
    onClickBackCard: (data: CardData) -> Unit,
    cards: List<CardData>,
    onAllCardClick: ()->Unit
) {
    Column(
        modifier = modifier
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = ShadowColorCard,
            )
            .background(cardColor)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextBoldBlack(
                modifier = Modifier.padding(2.dp),
                text = stringResource(id = R.string.cards),
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .height(24.dp)
                    .width(48.dp)
                    .clip(CircleShape)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onClickAddCard
                    ),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = null,
                    modifier = Modifier
                        .background(
                            color = primaryColor,
                            shape = CircleShape
                        )
                        .padding(4.dp).padding(end = 8.dp),
                    tint = Color.White,
                )
                Text(textAlign = TextAlign.Center,text = "${cards.size}", modifier = Modifier.fillMaxSize(1f))
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_chevron_right_x24),
                contentDescription = null,
                tint = Color(0xFF1A1818),
                modifier = Modifier
                    .padding(start = 4.dp)
                    .size(24.dp)
                    .clickable {
                        onAllCardClick()
                    },
            )
        }

        Box(
            modifier = Modifier
                .padding(top = 12.dp)
                .height(160.dp)
        ) {
            ItemCard(
                modifier = Modifier
                    .height(96.dp)
                    .fillMaxWidth()
                    //.aspectRatio(ratio = 1.5857725f)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) {
                        onClickBackCard.invoke(cards[1])
                    }, cardData = cards[1]
            )

            ItemCard(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height(96.dp)
                    .fillMaxWidth()
                    //.aspectRatio(ratio = 1.5857725f)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) {
                        onClickFrontCard.invoke(cards[2])
                    }, cardData = cards[2]
            )
        }

    }
}

@Composable
private fun ItemCard(
    modifier: Modifier = Modifier,
    cardData: CardData
) {
    val bankImageID = R.drawable.uzcard_logo
    val typeImageID = R.drawable.uzcard_logo
    val money = cardData.amount
    val type = "Uzcard"

    Column(
        modifier = modifier
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = ShadowColorCard,
            )
            .angledGradientBackground(
                colors = getColor(cardData.themeType), degrees = 65f
            )
            .padding(8.dp), verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            Modifier.fillMaxWidth()
        ) {

            Image(
                painter = painterResource(id = bankImageID),
                contentDescription = null,
                contentScale = ContentScale.Crop,

                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterStart)
            )

            Icon(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(18.dp),
                tint = Color.White,
                painter = painterResource(id = typeImageID),
                contentDescription = "card type",

                )
        }

        Row(
            Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = money.toString(),
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )
            Text(
                text = " " + stringResource(id = R.string.som),
                style = MaterialTheme.typography.bodySmall,
                color = Color(0x80FFFFFF)
            )
        }

        Box(
            modifier = Modifier, contentAlignment = Alignment.Center
        ) {
            Text(
                text = type,
                style = MaterialTheme.typography.bodySmall,
                color = Color.White,
            )
        }
    }
}

@Preview
@Composable
fun TwoCardsPreview() {
    MyCardsTwoCards(
        modifier = Modifier,
        onClickAddCard = {},
        onClickFrontCard = {},
        onClickBackCard = {},
        frontCard = CardData(
            "", "", 0, "", "", 0, 0, 0, false
        ),
        backCard = CardData(
            "", "", 0, "", "", 0, 0, 0, false
        ),
    )
}

@[Composable Preview]
fun MoreCardsPreview() {
    MyCardsMoreCards(
        onClickAddCard = {},
        onClickFrontCard = {},
        onClickBackCard = {},
        cards = listOf(
            CardData(
                "", "", 0, "", "", 0, 0, 0, false
            ),
            CardData(
                "", "", 0, "", "", 0, 0, 0, false
            ),
            CardData(
                "", "", 0, "", "", 0, 0, 0, false
            ),
            CardData(
                "", "", 0, "", "", 0, 0, 0, false
            ),

            ),
        onAllCardClick = {

        },
    )
}


fun getColor(type: Int): List<Color> = when (type) {
    0 -> {
        listOf(Color(0xFF0063B5), Color(0xFF00EBC8))
    }

    1 -> {
        listOf(Color(0xFF06693a), Color(0xFF20d970))
    }

    2 -> {
        listOf(Color(0xFF5b0a8a), Color(0xFFa9518d))
    }

    3 -> {
        listOf(Color(0xFF930709), Color(0xFFff9c63))
    }

    4 -> {
        listOf(Color(0xFF886e33), Color(0xFFffd645))
    }

    5 -> {
        listOf(Color(0xFF282a75), Color(0xFF009ffd))
    }

    6 -> {
        listOf(Color(0xFF191a1f), Color(0xFF55555f))
    }

    7 -> {
        listOf(Color(0xFF6c0f17), Color(0xFFbd1373))
    }

    else -> {
        listOf(Color(0xFFa95403), Color(0xFFecbe38))
    }
}
