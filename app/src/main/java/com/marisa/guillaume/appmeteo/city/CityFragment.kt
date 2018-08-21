package com.marisa.guillaume.appmeteo.city

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Toast
import com.marisa.guillaume.appmeteo.App
import com.marisa.guillaume.appmeteo.DataBase
import com.marisa.guillaume.appmeteo.R

class CityFragment : Fragment(), CityAdapteur.CityItemListener {

    private lateinit var database : DataBase
    private lateinit var cities: MutableList<City>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapteur: CityAdapteur

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = App.database
        cities = mutableListOf()
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_city,container,false)
        recyclerView = view.findViewById(R.id.cities_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cities = database.getAllCities()
        adapteur = CityAdapteur(cities, this)
        recyclerView.adapter = adapteur
    }

    override fun onCitySelected(city: City) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCityDeleted(city: City) {
        showDeleteCityDialog(city)
    }

    private fun showDeleteCityDialog(city: City) {
        val deleteCityFragment = DeleteCityDialogFragment.newInstance(city.name)

        deleteCityFragment.listener = object : DeleteCityDialogFragment.DeleteCityDialogListener{
            override fun onDialogPositiveClick() {
                deleteCity(city)
            }

            override fun onDialogNegativeClick() {
            }

        }

        deleteCityFragment.show(fragmentManager,"DeleteCityDialogFragment")
    }

    private fun deleteCity(city: City) {

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.fragment_city, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId){
            R.id.action_create_city -> {
                showCreateCityDialog()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showCreateCityDialog() {
        val createCityFragment = CreateCityDialogFragment()
        createCityFragment.listener = object: CreateCityDialogFragment.CreateCityDialogListener{
            override fun onDialogPositiveCLick(cityname: String) {
                saveCity(City(cityname))
            }

            override fun onDialogNegativeClick() {

            }

        }

        createCityFragment.show(fragmentManager,"CreateCityDialogFragment")
    }

    private fun saveCity(city: City) {
        if (database.createCity(city)){
            cities.add(city)
            adapteur.notifyDataSetChanged()
        }
        else{
            Toast.makeText(context,"Could not create city",Toast.LENGTH_SHORT).show()
        }
    }

}