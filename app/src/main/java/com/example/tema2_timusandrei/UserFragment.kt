package com.example.tema2_timusandrei

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.tema2_timusandrei.adapters.UserAdapter
import com.example.tema2_timusandrei.interfaces.OnUserItemClick
import com.example.tema2_timusandrei.models.User
import org.json.JSONArray
import org.json.JSONObject

class UserFragment: Fragment() {
    private val userList :ArrayList<User> = ArrayList<User>()
    private val onUserItemClick :OnUserItemClick = OnUserItemClick {  }
    private val userAdapter : UserAdapter = UserAdapter(userList, onUserItemClick)
    companion object {
        fun newInstance() : Fragment {
            return Fragment()
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.user_fragment, container, false)
        setUpRecyclerView(view)
        getPosts()
        return view
    }
    private fun setUpRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.list);
        val linearLayoutManager = LinearLayoutManager(view.context);
        userList.clear()
        recyclerView.layoutManager = linearLayoutManager
        val adapter = UserAdapter(userList, OnUserItemClick {movie ->
            //ceva aici
        })
    }

    fun getPosts(){
        val queue = Volley.newRequestQueue(activity)

        val url = "https://jsonplaceholder.typicode.com/users";

        val getPostsRequest = StringRequest(
                Request.Method.GET,
                url,
                {response ->
                    handlePostResponse(response)
                },
                {error->
                    Toast.makeText(
                            activity,
                            "ERROR get posts failed with error: ${error.message}",
                            Toast.LENGTH_SHORT
                    ).show()
                }
        )
        queue.add(getPostsRequest)
    }
    private fun handlePostResponse(result:String) {
        val postJsonArray = JSONArray(result)
        for (index in 0 until postJsonArray.length()) {
            val postJson: JSONObject? = postJsonArray[index] as? JSONObject
            postJson?.let {
                val id = postJson.getInt("id")
                val name = postJson.getString("name")
                val userName = postJson.getString("username")
                val postUser: User = User(id, name, userName)
                userList.add(postUser)
            }
        }
        userAdapter.notifyDataSetChanged()
    }
}