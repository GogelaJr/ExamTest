package com.example.examtest.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examtest.Locations
import com.example.examtest.R
import com.example.examtest.RecycleViewAdapter

class HomepageFragment: Fragment(R.layout.homepage_fragment) {

    private lateinit var recycleViewAdapter: RecycleViewAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)

        val activity = getActivity()

        recycleViewAdapter = RecycleViewAdapter(getData())
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = recycleViewAdapter


    }
    private fun getData(): List<Locations> {

        val locations = ArrayList<Locations>()

        locations.add(Locations(
            1,
            "Tbilisi",
            "https://i.natgeofe.com/n/e1601e92-9017-4b02-ad0e-72c531b09126/tbilisi-city-guide-roofs.jpg",
            "tbilisi is a old city"))

        locations.add(Locations(2,
            "Kutaisi",
            "https://i2.wp.com/www.golivegotravel.nl/wp-content/uploads/2020/06/Colchis-Fontain-Kutaisi.jpeg?resize=770%2C514&ssl=1",
            "Kutaisi is the old Capital of Georgia"))


        return locations

    }
}