package tech.tsdev.github_management.model

import com.google.gson.annotations.SerializedName


//@SerializedName이란? -> gson에서 제공하는 역직렬화 어노테이션이다
// 역직렬화란? -> 여기서는 json 객체를 코틀린 객체로 변환 시킨다는 의미
//그래서 @serializedName안에 있는 value 값대신에 코틀린에서는 밖에서 정의 한 것으로 사용하겠다는 의미
//@SerializedName("total_count") val totalCount: Int -> json 파일에 있는 total_count의 값을 가져온 값을 여기서는 totalCount로 부르겠다.
data class SearchUserData(
    val incomplete_results: Boolean,
    @SerializedName("items") val items: List<Item>,
    @SerializedName("total_count") val totalCount: Int
)

data class Item(
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("events_url") val eventsUrl: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val gravatar_id: String,
    val html_url: String,
    val id: Int,
    val login: String,
    val node_id: String,
    val organizations_url: String,
    val received_events_url: String,
    val repos_url: String,
    val score: Double,
    val site_admin: Boolean,
    val starred_url: String,
    val subscriptions_url: String,
    val type: String,
    val url: String,
    @Transient var totalStar: Int,
    @Transient var followers: List<Item>
)