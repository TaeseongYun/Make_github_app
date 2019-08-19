package tech.tsdev.github_management.model.repo

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

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
    @SerializedName("watchers_count")  val watchersCount: Int,
    val url: String
) {

    fun simpleDateCreateAt(createWhen: String): String {
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
            .parse(createWhen.substringBefore("T"))

        val dateFormat = SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH)


        return "Create At ${dateFormat.format(date)}"
    }

    fun testCreateAt(createWhen: String): String = createWhen.substringBefore("T")
}

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