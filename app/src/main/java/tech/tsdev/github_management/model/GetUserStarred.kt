package tech.tsdev.github_management.model

import com.google.gson.annotations.SerializedName

data class GetUserStarred(
    val archived: Boolean,
    val created_at: String,
    val default_branch: String,
    val description: String,
    val fork: Boolean,
    val forks: Int,
    @SerializedName("forks_count")  val forksCount: Int,
    val id: Int,
    val language: String,
    val name: String,
    val open_issues: Int,
    val open_issues_count: Int,
    val owner: Owner,
    @SerializedName("stargazers_count")  val stargazersCount: Int,
    val updated_at: String,
    val url: String,
    val watchers: Int,
    val watchers_count: Int
)

data class StarredOwner(
    @SerializedName("avatar_url")  val avatarUrl: String,
    val login: String,
    val site_admin: Boolean,
    val starred_url: String,
    val type: String,
    val url: String
)