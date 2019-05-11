package tech.tsdev.github_management.model

import com.google.gson.annotations.SerializedName

data class UserFollowersFollowingList(
    @SerializedName("avatar_url")  val avatarUrl: String,
    val login: String
)