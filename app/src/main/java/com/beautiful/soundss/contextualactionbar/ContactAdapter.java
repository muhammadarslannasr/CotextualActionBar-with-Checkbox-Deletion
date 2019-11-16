package com.beautiful.soundss.contextualactionbar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ArslanNasr on 9/18/2018.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    ArrayList<Contact> contactsList = new ArrayList<>();
    MainActivity mainActivity;
    Context context;

    public ContactAdapter(ArrayList<Contact> contactsList, Context context) {
        this.contactsList = contactsList;
        this.context = context;
        mainActivity = (MainActivity) context;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout,parent,false);
        ContactViewHolder contactViewHolder = new ContactViewHolder(view,mainActivity);


        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {

        Contact contact = contactsList.get(position);

        holder.img_id.setImageResource(contact.getImage());
        holder.name.setText(contact.getName());
        holder.email.setText(contact.getEmail());

        if(!mainActivity.is_in_action_mode){
            holder.check_list_item.setVisibility(View.GONE);
        }else {
            holder.check_list_item.setVisibility(View.VISIBLE);
            holder.check_list_item.setChecked(false);
        }

    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView img_id;
        TextView name,email;
        CheckBox check_list_item;
        MainActivity mainActivity;
        CardView cardView;

        public ContactViewHolder(View itemView,MainActivity mainActivity) {
            super(itemView);

            img_id = itemView.findViewById(R.id.img_id);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            check_list_item = itemView.findViewById(R.id.check_list_item);
            this.mainActivity = mainActivity;
            cardView = itemView.findViewById(R.id.cardView);
            cardView.setOnLongClickListener(mainActivity);
            check_list_item.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            mainActivity.prepareSelection(view,getAdapterPosition());
        }
    }

    public void updateAdapter(ArrayList<Contact> list){

        for (Contact contact: list){

            contactsList.remove(contact);
        }

        notifyDataSetChanged();
    }
}
