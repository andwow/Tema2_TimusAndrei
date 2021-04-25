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
import com.example.tema2_timusandrei.adapters.AlbumAdapter
import com.example.tema2_timusandrei.adapters.UserAdapter
import com.example.tema2_timusandrei.models.Album
import com.example.tema2_timusandrei.models.User
import org.json.JSONArray
import org.json.JSONObject

class AlbumFragment(userId: Int):Fragment() {
    private val userId :Int = userId;
    private val albumList:ArrayList<Album> = ArrayList()
    private val albumAdapter : AlbumAdapter = AlbumAdapter(albumList)
    companion object {
        fun newInstance(userId: Int) : AlbumFragment {
            return AlbumFragment(userId)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.album_fragment, container, false)
        setUpRecyclerView(view)
        getPosts()
        return view
    }
    private fun setUpRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.album_list);
        val linearLayoutManager = LinearLayoutManager(view.context);
        albumList.clear()
        recyclerView.layoutManager = linearLayoutManager
        val adapter = AlbumAdapter(albumList)
        recyclerView.adapter=adapter
    }

    fun getPosts(){
        val queue = Volley.newRequestQueue(activity)

        val url = "https://jsonplaceholder.typicode.com/albums";

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
                if(postJson.getInt("userId") == userId)
                {
                    val id = postJson.getInt("id")
                    val name = postJson.getString("name")
                    val postAlbum: Album = Album(id, name)
                    albumList.add(postAlbum)
                }
            }
        }
        albumAdapter.notifyDataSetChanged()
    }
}