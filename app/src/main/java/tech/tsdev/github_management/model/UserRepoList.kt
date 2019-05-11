package tech.tsdev.github_management.model

import com.google.gson.annotations.SerializedName

data class UserRepoList(
    val description: String?,
    val fork: Boolean,
    val forks: Int,
    @SerializedName("forks_count") val forksCount: Int,
    val language: String,
    val name: String,
    val owner: UserRepoOwner,
    @SerializedName("stargazers_count")  val stargazersCount: Int
)

data class UserRepoOwner(
    @SerializedName("avatar_url") val avatarUrl: String,
    val login: String
)