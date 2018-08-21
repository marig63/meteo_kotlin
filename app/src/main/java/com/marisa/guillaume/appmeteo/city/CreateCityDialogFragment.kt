package com.marisa.guillaume.appmeteo.city

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.InputType
import android.widget.EditText
import com.marisa.guillaume.appmeteo.R

class CreateCityDialogFragment : DialogFragment(){
    interface  CreateCityDialogListener{
        fun onDialogPositiveCLick(cityname:String)
        fun onDialogNegativeClick()
    }

    var listener: CreateCityDialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)

        val input = EditText(context)
        with(input){
            inputType = InputType.TYPE_CLASS_TEXT
            hint = context.getString(R.string.createcity_cityhint)
        }

        builder.setTitle(getString(R.string.createcity_title))
                .setView(input)
                .setPositiveButton(getString(R.string.createcty_positive),DialogInterface.OnClickListener{
                    _, _ ->
                    listener?.onDialogPositiveCLick(input.text.toString())
                }).setNegativeButton(getString(R.string.createcity_negative),DialogInterface.OnClickListener{
                    _, _ ->
                    dialog.cancel()
                    listener?.onDialogNegativeClick()
                })

        return builder.create()
    }
}