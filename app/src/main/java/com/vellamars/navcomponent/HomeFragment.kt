package com.vellamars.navcomponent

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.Room
import com.vellamars.navcomponent.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

   // var ListMedicine: MutableList<Medicine> = mutableListOf()
   // lateinit var adaptador: AdaptadorMedicine

    private lateinit var room:DBMedicine

    lateinit var medicine: Medicine
    lateinit var  test : OtherFragment

    //@OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentHomeBinding.inflate(inflater, container, false)



        room = Room.databaseBuilder(requireContext(), DBMedicine::class.java, "dbMedicine").build()


        binding.button.setOnClickListener {
            if(binding.textView.text.isNullOrEmpty()){
                msg("Please, scan medicine!")
                return@setOnClickListener
            }else{
                medicine = Medicine(
                    UUID = addUUID(),
                    medicineItem = binding.textView.text.toString() ,
                    //medicineDate = toDayDate()
                )
                addMedicine(room, medicine)
            }
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener{
            val textContent:String = binding.editTextText.text.toString()
            binding.textView.text = textContent

//            val action = HomeFragmentDirections.actionHomeFragmentToOtherFragment(textContent)
//            findNavController().navigate(action)
//
//            //val bundle = Bundle()
//            //bundle.putString("content",textContent)
        }


    }

    fun addMedicine(room:DBMedicine, medicine: Medicine){
        lifecycleScope.launch {
            room.daoMedicine().addMedicine(medicine)
            test.getMedicine(room)
            clearInfo()

        }
    }


    private fun msg(text: String){
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }

    private fun clearInfo(){
       // medicine.medicineDate = ""
        medicine.medicineItem = ""
        binding.textView.text = ""
        msg("Info deleted!")
    }

    private fun addUUID(): String {
        return UUID.randomUUID().toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun toDayDate(): String {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a"))
    }


    //@OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun onResume() {
        super.onResume()
    }

    //@OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun onPause() {
        super.onPause()

    }

    //@OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}