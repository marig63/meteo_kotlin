package com.marisa.guillaume.appmeteo.city

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.Toast
import com.marisa.guillaume.appmeteo.App
import com.marisa.guillaume.appmeteo.DataBase
import com.marisa.guillaume.appmeteo.R

class CityFragment : Fragment(){

    private lateinit var database : DataBase
    private lateinit var cities: MutableList<City>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = App.database
        cities = mutableListOf()
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_city,container,false)
        return view
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
        }
        else{
            Toast.makeText(context,"Could not create city",Toast.LENGTH_SHORT).show()
        }
    }

}