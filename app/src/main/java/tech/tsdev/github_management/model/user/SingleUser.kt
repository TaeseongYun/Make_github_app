package tech.tsdev.github_management.model.user

import com.google.gson.annotations.SerializedName

data class SingleUser(
    @SerializedName("avatar_url") val avatarUrl: String,
    val bio: String,
    val blog: String,
    val collaborators: Int,
    val company: Any,
    @SerializedName("created_at") val createdAt: String,
    val disk_usage: Int,
    val email: String,
    val events_url: String,
    @SerializedName("followers") val followers: Int,
    val followers_url: String,
    @SerializedName("following") val following: Int,
    val following_url: String,
    val gists_url: String,
    val gravatar_id: String,
    val hireable: Any,
    val html_url: String,
    val id: Int,
    val location: String,
    val login: String,
    val name: String,
    val node_id: String,
    val organizations_url: String,
    val owned_private_repos: Int,
    val plan: Plan,
    val private_gists: Int,
    val public_gists: Int,
    val public_repos: Int,
    val received_events_url: String,
    val repos_url: String,
    val site_admin: Boolean,
    val starred_url: String,
    val subscriptions_url: String,
    val total_private_repos: Int,
    val two_factor_authentication: Boolean,
    val type: String,
    val updated_at: String,
    val url: String
) {
    fun joinToGithubDate(createAtDate: String): String {
        val substr = createAtDate.substringBefore("T")
        return "가입 날짜 : $substr"
    }
}


data class Plan(
    val collaborators: Int,
    val name: String,
    val private_repos: Int,
    val space: Int
)