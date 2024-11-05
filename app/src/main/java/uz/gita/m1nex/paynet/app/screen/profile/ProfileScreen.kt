package uz.gita.m1nex.paynet.app.screen.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.m1nex.core.hiltScreenModel
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.dialog.BottomSheetCallCenterContent
import uz.gita.m1nex.paynet.app.ui.dialog.BottomSheetCallCenterProfile
import uz.gita.m1nex.paynet.app.ui.dialog.BottomSheetInfoContent
import uz.gita.m1nex.paynet.app.ui.dialog.BottomSheetInfoProfile
import uz.gita.m1nex.paynet.app.ui.dialog.LogOutDialog
import uz.gita.m1nex.paynet.app.ui.dialog.OptionBottomSheetContent
import uz.gita.m1nex.paynet.app.ui.dialog.OptionBottomSheetDialog
import uz.gita.m1nex.paynet.app.ui.theme.BackgroundLight
import uz.gita.m1nex.paynet.app.ui.theme.BackgroundWhite90
import uz.gita.m1nex.paynet.app.ui.theme.Gray70
import uz.gita.m1nex.paynet.app.ui.theme.textColorLight
import uz.gita.m1nex.paynet.app.ui.theme.textColorLight80
import uz.gita.m1nex.paynet.app.ui.theme.textColorLight90
import uz.gita.m1nex.presenter.screenmodel.profile.ProfileContract


class ProfileScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val screenViewModel: ProfileContract.Model = hiltScreenModel()
        val uiState = screenViewModel.collectAsState()
        val context = LocalContext.current
        val bottomSheetNavigator = LocalNavigator.current
        val showDialog = remember { mutableStateOf(0) }
        screenViewModel.collectSideEffect {
            when (it) {
                ProfileContract.SideEffect.OpenRankSheet -> {
                    showDialog.value = 1
                }

                ProfileContract.SideEffect.OpenCallSheet -> {
                    showDialog.value = 2
                }

                ProfileContract.SideEffect.OpenInfoSheet -> {
                    showDialog.value = 3
                }

                else -> {}
            }
        }
        when (showDialog.value) {
            0 -> {}
            1 -> {
                ModalBottomSheet(onDismissRequest = { showDialog.value = 0 }) {
                    BottomSheetInfoContent()
                }
            }

            2 -> {
                ModalBottomSheet(onDismissRequest = { showDialog.value = 0 }) {
                    BottomSheetCallCenterContent()
                }
            }

            3 -> {
                ModalBottomSheet(onDismissRequest = { showDialog.value = 0 }) {
                    OptionBottomSheetContent(
                        onFirst = {
                            val uri =
                                "https://assets-global.website-files.com/63a7038e6eb0c1f38cd4d11f/659e7e324fed06afd1dbacfb_%D0%BE%D1%84%D0%B5%D1%80%D1%82%D0%B0%20%D0%BC%D0%BE%D0%B1%D0%B8%D0%BB%D0%BA%D0%B0%202024.pdf"
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                            context.startActivity(intent)
                        }, onSecond = {
                            val uri =
                                "https://assets-global.website-files.com/63a7038e6eb0c1f38cd4d11f/6602de34fdf497829debb1df_%D0%BE%D1%84%D0%B5%D1%80%D1%82%D0%B0%20%D1%84%D0%B8%D0%BD%D0%B0%D0%BB%20final.pdf"
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                            context.startActivity(intent)
                        }, onThird = {
                            val uri =
                                "https://uploads-ssl.webflow.com/63a7038e6eb0c1f38cd4d11f/64b8ecef9bd4e117c602cbc9_%D0%BE%D1%84%D0%B5%D1%80%D1%82%D0%B0%20%D0%BA%D0%BE%D1%88%D0%B5%D0%BB%D1%8C%D0%BA%D0%B0%20(2).pdf"
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                            context.startActivity(intent)
                        })
                }
            }
        }
        ProfileScreenContent(
            uiState,
            onEventDispatcher = screenViewModel::onEventDispatcher
        )

    }

    @Composable
    fun ProfileScreenContent(
        uiState: State<ProfileContract.UiState>,
        onEventDispatcher: (ProfileContract.Intent) -> Unit,
    ) {
        val showDialog = remember { mutableStateOf(false) }
        LaunchedEffect(key1 = 1, block = {
            when (uiState.value) {
                ProfileContract.UiState.LogOut -> {

                }

                else -> {}
            }
        })

        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundLight)
        ) {
            TopSection(onClickBack = {
                onEventDispatcher.invoke(ProfileContract.Intent.Back)
            })

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackgroundLight)
                    .verticalScroll(enabled = true, state = scrollState)
            ) {

                StatusSection {
                    onEventDispatcher.invoke(ProfileContract.Intent.UserStatus)
                }
                SupportSection {
                    onEventDispatcher.invoke(it)
                }
                InfoSection(
                    onClickAbout = {
                        onEventDispatcher.invoke(ProfileContract.Intent.About)
                    },
                    onClickInfo = {
                        onEventDispatcher.invoke(ProfileContract.Intent.Info)
                    })
                SettingsSection {
                    onEventDispatcher.invoke(ProfileContract.Intent.Settings)
                }
                MapSection {
                    "MapSection"
                    onEventDispatcher.invoke(ProfileContract.Intent.Map)
                }
                RankingsSection {
                    "ProfileContract.SideEffect.OpenRankSheet dan viewmodelga xabar ketdi"
                    onEventDispatcher.invoke(ProfileContract.Intent.Rank)
                }
                LogOutSection(onClick = {
                    showDialog.value = true
                })
                LogOutDialog(setShowDialog = {
                    showDialog.value = it
                }, isVisible = showDialog.value,
                    logOutRequest = {
                        onEventDispatcher.invoke(ProfileContract.Intent.LogOut)
                    }, cancelRequest = {
                        showDialog.value = false
                    })
            }
        }
    }


    @Composable
    fun TopSection(
        modifier: Modifier = Modifier,
        onClickBack: () -> Unit
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_navigation_arrow_left_x24),
                contentDescription = null,
                tint = Gray70,
                modifier = Modifier
                    .padding(16.dp)
                    .size(24.dp)
                    .clickable { onClickBack.invoke() }
            )

            Text(
                stringResource(id = R.string.profile),
                fontSize = 20.sp,
                color = textColorLight,
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
            )
        }
    }

    @Composable
    fun StatusSection(modifier: Modifier = Modifier, onClick: () -> Unit) {
        val context = LocalContext.current
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .shadow(elevation = 4.dp, RoundedCornerShape(16.dp), spotColor = Color.Gray)
                .clip(RoundedCornerShape(16.dp))
                .background(BackgroundWhite90)
                .padding(horizontal = 12.dp),
        )
        {
            Text(
                text = stringResource(R.string.you_are_not_verified),
                color = textColorLight,
                fontFamily = FontFamily(Font(R.font.pnfont_medium)),
                fontSize = 18.sp,
                modifier = Modifier.padding(
                    top = 24.dp,
                    start = 16.dp,
                    end = 16.dp
                )
            )


            StatusEventSection()

            IdentifyButton {
                onClick.invoke()
            }
        }
    }


    @Composable
    fun StatusEventSection(modifier: Modifier = Modifier) {
        val context = LocalContext.current
        var isUserStatus by remember { mutableStateOf(true) }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(top = 14.dp)
                .shadow(elevation = 4.dp, RoundedCornerShape(16.dp), spotColor = Color.White)
                .clip(RoundedCornerShape(16.dp))
                .background(BackgroundWhite90)
                .padding(6.dp)
        ) {
            Image(
                painter = if (isUserStatus) painterResource(id = R.drawable.ic_action_anonymous_status) else painterResource(
                    id = R.drawable.ic_action_confirmed_status
                ),
                contentDescription = null,
                modifier = Modifier
                    .padding(6.dp)
                    .size(34.dp)
            )

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = AbsoluteAlignment.Left,
                modifier = Modifier
                    .wrapContentSize()
            ) {
                Text(
                    stringResource(id = R.string.your_status),
                    color = Gray70,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_medium))
                )

                Text(
                    text = stringResource(id = if (isUserStatus) R.string.anonymous else R.string.identified),
                    color = textColorLight,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold))
                )
            }
        }
    }


    @Composable
    fun IdentifyButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
        val context = LocalContext.current
        var isUserStatus by remember { mutableStateOf(true) }
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .padding(top = 22.dp, bottom = 12.dp)
                .fillMaxWidth()
                .height(46.dp)
                .clickable { onClick.invoke() }
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xff01a827))
        ) {
            Text(
                stringResource(id = if (isUserStatus) R.string.identity_verification else R.string.see_data),
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_medium)),
            )
        }
    }


    @Composable
    fun SupportSection(
        modifier: Modifier = Modifier,
        onEventDispatcher: (ProfileContract.Intent) -> Unit
    ) {
        val context = LocalContext.current
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 14.dp)
                .shadow(elevation = 4.dp, RoundedCornerShape(16.dp), spotColor = Color.Gray)
                .clip(RoundedCornerShape(16.dp))
                .background(BackgroundWhite90)
        ) {
            Text(
                stringResource(id = R.string.support),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_medium)),
                color = textColorLight80,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 8.dp)
            )


            ProfileCategorySection(
                imageRes = R.drawable.ic_operations_comment,
                stringRes = R.string.chat_helper,
                onClick = {
                    val intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://chat.paynet.uz/"))
                    startActivity(context, intent, Bundle())

                },
                modifier = Modifier
                    .padding(top = 8.dp, start = 12.dp)
            )

            ProfileCategorySection(
                imageRes = R.drawable.ic_action_phone_alt,
                stringRes = R.string.call,
                onClick = {
                    onEventDispatcher.invoke(ProfileContract.Intent.CallCenter)
                },
                modifier = Modifier
                    .padding(top = 24.dp, start = 12.dp)
            )

            ProfileCategorySection(
                imageRes = R.drawable.ic_action_mail,
                stringRes = R.string.write_mail,
                onClick = {
                    val emailIntent = Intent(
                        Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", "support@paynet.uz", null
                        )
                    )
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "This is my subject text")
                    context.startActivity(Intent.createChooser(emailIntent, null))
                },
                modifier = Modifier
                    .padding(top = 24.dp, start = 12.dp, bottom = 14.dp)
            )
        }
    }


    @Composable
    fun InfoSection(
        modifier: Modifier = Modifier,
        onClickAbout: () -> Unit,
        onClickInfo: () -> Unit
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .shadow(elevation = 4.dp, RoundedCornerShape(16.dp), spotColor = Color.Gray)
                .clip(RoundedCornerShape(16.dp))
                .background(BackgroundWhite90)
        ) {
            Text(
                stringResource(id = R.string.useful_information),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_medium)),
                color = textColorLight80,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 8.dp)
            )


            ProfileCategorySection(
                imageRes = R.drawable.ic_action_info,
                stringRes = R.string.about_paynet,
                onClick = { onClickAbout.invoke() },
                modifier = Modifier
                    .padding(top = 14.dp, start = 12.dp)
                    .height(20.dp)
            )

            ProfileCategorySection(
                imageRes = R.drawable.ic_action_question,
                stringRes = R.string.info_profile,
                onClick = { onClickInfo.invoke() },
                modifier = Modifier
                    .padding(top = 26.dp, start = 12.dp, bottom = 12.dp)
            )
        }
    }


    @Composable
    fun SettingsSection(modifier: Modifier = Modifier, onClick: () -> Unit) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 12.dp)
                .shadow(elevation = 4.dp, RoundedCornerShape(16.dp), spotColor = Color.Gray)
                .clip(RoundedCornerShape(16.dp))
                .background(BackgroundWhite90)
        ) {

            ProfileCategorySection(
                imageRes = R.drawable.ic_actions_settings,
                stringRes = R.string.app_settings,
                onClick = { onClick.invoke() },
                modifier = Modifier
                    .padding(top = 12.dp, start = 12.dp, bottom = 12.dp)
            )
        }
    }

    @Composable
    fun MapSection(modifier: Modifier = Modifier, onClick: (() -> Unit)) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 12.dp)
                .shadow(elevation = 4.dp, RoundedCornerShape(16.dp), spotColor = Color.Gray)
                .clip(RoundedCornerShape(16.dp))
                .background(BackgroundWhite90)
        ) {

            ProfileCategorySection(
                imageRes = R.drawable.location,
                stringRes = R.string.paynet_offices,
                onClick = { onClick.invoke() },
                modifier = Modifier
                    .padding(top = 12.dp, start = 12.dp, bottom = 12.dp)
            )
        }
    }

    @Composable
    fun RankingsSection(modifier: Modifier = Modifier, onClick: () -> Unit) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 14.dp)
                .shadow(elevation = 4.dp, RoundedCornerShape(16.dp), spotColor = Color.Gray)
                .clip(RoundedCornerShape(16.dp))
                .background(BackgroundWhite90)
        ) {

            ProfileCategorySection(
                imageRes = R.drawable.ic_operations_star_v2,
                stringRes = R.string.rank_to_app,
                onClick = { onClick.invoke() },
                modifier = Modifier
                    .padding(top = 12.dp, start = 12.dp, bottom = 14.dp)
                    .height(22.dp)
            )
        }
    }

    @Composable
    fun LogOutSection(
        modifier: Modifier = Modifier,
        onClick: (() -> Unit)? = null
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 14.dp, bottom = 24.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(BackgroundLight)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .wrapContentSize()
                    .padding(start = 16.dp, end = 16.dp, top = 14.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { onClick?.invoke() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_action_sign_in),
                    contentDescription = null,
                    tint = Red,
                    modifier = Modifier.size(24.dp)
                )

                Text(
                    stringResource(id = R.string.log_out_profile),
                    fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                    color = Red,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
        }
    }

    @SuppressLint("ResourceType")
    @Composable
    fun ProfileCategorySection(
        modifier: Modifier = Modifier,
        onClick: (() -> Unit)? = null,
        imageRes: Int,
        stringRes: Int,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable { onClick?.invoke() }
        ) {
            Icon(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                tint = Color(0xff6E7279),
                modifier = Modifier.size(24.dp)
            )

            Text(
                stringResource(id = stringRes),
                fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                color = textColorLight90,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 12.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(id = R.drawable.ic_chevron_right_x24),
                contentDescription = null,
                tint = Color(0xff3c4553),
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(20.dp)
            )
        }
    }


}