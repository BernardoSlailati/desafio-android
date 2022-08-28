package com.slailati.android.domain.mapper

import com.slailati.android.data.local.model.UserEntity
import com.slailati.android.data.remote.model.UserApi
import com.slailati.android.domain.model.User

val fakeFirstUserEntity = UserEntity(
    id = 0,
    userId = 1,
    name = "name1",
    img = "img1",
    username = "username1"
)

val fakeFirstUser = User(
    id = 1,
    name = "name1",
    img = "img1",
    username = "username1"
)

val fakeSecondUserEntity = UserEntity(
    id = 0,
    userId = 2,
    name = "name2",
    img = "img2",
    username = "username2"
)

val fakeSecondUser = User(
    id = 2,
    name = "name2",
    img = "img2",
    username = "username2"
)

val fakeThirdUserEntity = UserEntity(
    id = 0,
    userId = 3,
    name = "name3",
    img = "img3",
    username = "username3"
)

val fakeThirdUser = User(
    id = 3,
    name = "name3",
    img = "img3",
    username = "username3"
)

val fakeUserEntityList = listOf(fakeFirstUserEntity, fakeSecondUserEntity, fakeThirdUserEntity)
val fakeUserDomainList = listOf(fakeFirstUser, fakeSecondUser, fakeThirdUser)

val fakeFirstUserApiCorrect = UserApi(
    id = 1,
    name = "name1",
    img = "img1",
    username = "username1"
)

val fakeFirstUserApiWrong = UserApi(
    id = null,
    name = null,
    img = "img1",
    username = "username1"
)

val fakeFirstUserCorrect = User(
    id = 1,
    name = "name1",
    img = "img1",
    username = "username1"
)

val fakeFirstUserWrong = User(
    id = -1,
    name = "",
    img = "img1",
    username = "username1"
)

val fakeSecondUserApiCorrect = UserApi(
    id = 2,
    name = "name2",
    img = "img2",
    username = "username2"
)

val fakeSecondUserApiWrong = UserApi(
    id = null,
    name = null,
    img = null,
    username = "username2"
)

val fakeSecondUserCorrect = User(
    id = 2,
    name = "name2",
    img = "img2",
    username = "username2"
)

val fakeSecondUserWrong = User(
    id = -1,
    name = "",
    img = "",
    username = "username2"
)


val fakeUserApiListAllCorrect =
    listOf(fakeFirstUserApiCorrect, fakeSecondUserApiCorrect)
val fakeUserApiListHalfCorrect = listOf(fakeFirstUserApiCorrect, fakeSecondUserApiWrong)
val fakeUserApiListAllWrong = listOf(fakeFirstUserApiWrong, fakeSecondUserApiWrong)

val fakeUserDomainListAllCorrect = listOf(fakeFirstUserCorrect, fakeSecondUserCorrect)
val fakeUserDomainListHalfCorrect = listOf(fakeFirstUserCorrect, fakeSecondUserWrong)
val fakeUserDomainListAllWrong = listOf(fakeFirstUserWrong, fakeSecondUserWrong)

val fakeFirstFavoriteUserEntity = UserEntity(
    id = 1,
    userId = 101,
    name = "name1",
    img = "img1",
    username = "username1"
)


val fakeSecondFavoriteUserEntity = UserEntity(
    id = 2,
    userId = 102,
    name = "name2",
    img = "img2",
    username = "username2"
)

val fakeThirdFavoriteUserEntity = UserEntity(
    id = 3,
    userId = 103,
    name = "name3",
    img = "img3",
    username = "username3"
)

val fakeFavoriteUserToBeInserted = User(
    id = 104,
    name = "name4",
    img = "img4",
    username = "username4"
)

val fakeFavoriteUserToBeDeleted = User(
    id = 102,
    name = "name2",
    img = "img2",
    username = "username2"
)

val fakeFavoritesUserEntityList = listOf(fakeFirstFavoriteUserEntity, fakeSecondFavoriteUserEntity, fakeThirdFavoriteUserEntity)