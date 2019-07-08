package mx.com.navigationdrawer

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_fragment1.*
import mx.com.navigationdrawer.adapters.CustomRecyclerAdapter
import mx.com.navigationdrawer.model.Course
import mx.com.navigationdrawer.utils.Helper


class Fragment1: Fragment() {

    var courses = ArrayList<Course>()
    var filteredCourses = ArrayList<Course>()
    var coursesAdapter = CustomRecyclerAdapter(courses)
    var filteredAdapter = CustomRecyclerAdapter(filteredCourses)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_fragment1, container, false)
        val rvRecyclerView = rootView?.findViewById(R.id.sample_recyclerView) as RecyclerView
        courses = Helper.Companion.getVersionsList()
        coursesAdapter = CustomRecyclerAdapter(courses)
        rvRecyclerView.layoutManager = LinearLayoutManager(activity)
        rvRecyclerView.adapter = coursesAdapter
        setHasOptionsMenu(true)
        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        val searchItem = menu.findItem(R.id.menu_search)
        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            val editext = searchView.findViewById<EditText>(android.support.v7.appcompat.R.id.search_src_text)
            editext.hint = "Search here..."

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextChange(newText: String): Boolean {
                    if (newText.isEmpty()){
                        sample_recyclerView.adapter = coursesAdapter
                    }else{
                        filteredCourses.clear()
                        courses.forEach{
                            if (it.courseName.toLowerCase().contains(newText.toLowerCase())){
                                filteredCourses.add(it)
                            }
                        }
                        sample_recyclerView.adapter = filteredAdapter
                    }
                    return true
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    return true
                }
            })
        }
        super.onCreateOptionsMenu(menu, inflater)
    }
}
