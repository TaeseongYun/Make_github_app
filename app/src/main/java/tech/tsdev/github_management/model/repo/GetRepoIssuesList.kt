package tech.tsdev.github_management.model.repo

import com.google.gson.annotations.SerializedName

data class GetRepoIssuesList(
    val assignee: Any,
    val assignees: List<Any>,
    val author_association: String,
    val body: String,
    val closed_at: Any,
    val comments: Int,
    @SerializedName("comments_url")  val commentsUrl: String,
    @SerializedName("created_at") val createdAt: String,
    val events_url: String,
    val html_url: String,
    val id: Int,
    val labels: List<Any>,
    val labels_url: String,
    val locked: Boolean,
    val milestone: Any,
    val number: Int,
    val repository_url: String,
    val state: String,
    val title: String,
    val updated_at: String,
    //클릭시 넘어가야 하기 때문에 삭제하면 안됨
    val url: String,
    val user: User
)

data class User(
    @SerializedName("avatar_url") val avatarUrl: String,
    val events_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val gravatar_id: String,
    val html_url: String,
    val login: String,
    val organizations_url: String,
    val received_events_url: String,
    val repos_url: String,
    val site_admin: Boolean,
    val starred_url: String,
    val subscriptions_url: String,
    val type: String,
    val url: String
)