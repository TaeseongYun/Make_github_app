package tech.tsdev.github_management.model

import com.google.gson.annotations.SerializedName

data class GetSingleRepo(
    @SerializedName("created_at")  val createdAt: String,
    val description: String?,
    val forks: Int,
    @SerializedName("forks_count") val forksCount: Int,
    @SerializedName("full_name")  val fullName: String,
    val language: String,
    val name: String,
    val open_issues: Int,
    @SerializedName("open_issues_count")  val openIssuesCount: Int,
    val owner: Owner,
    val permissions: Permissions,
    val `private`: Boolean,
    val pulls_url: String,
    val pushed_at: String,
    val size: Int,
    @SerializedName("stargazers_count")  val stargazersCount: Int,
    val stargazers_url: String,
    val statuses_url: String,
    @SerializedName("subscribers_count") val subscribersCount: Int,
    val updated_at: String,
    val watchers: Int,
    @SerializedName("watchers_count")  val watchersCount: Int
)

data class Owner(
    @SerializedName("avatar_url")  val avatarUrl: String,
    val events_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val gravatar_id: String,
    val html_url: String,
    val id: Int,
    val login: String,
    val site_admin: Boolean,
    val type: String,
    val url: String
)

data class Permissions(
    val admin: Boolean,
    val pull: Boolean,
    val push: Boolean
)