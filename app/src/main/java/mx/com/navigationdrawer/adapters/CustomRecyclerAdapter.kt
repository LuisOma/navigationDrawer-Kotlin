package mx.com.navigationdrawer.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import mx.com.navigationdrawer.R
import mx.com.navigationdrawer.model.Course


class CustomRecyclerAdapter(val courseList: ArrayList<Course>) :
    RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(courseList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return courseList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(course: Course) {
            val txtTitle = itemView.findViewById<TextView>(R.id.appOSTitle_txtVw) as TextView
            val txtContent = itemView.findViewById<TextView>(R.id.appOSDetails_txtVw) as TextView
            txtTitle.text = course.courseName
            txtContent.text = course.courseDescrip
        }
    }
}
