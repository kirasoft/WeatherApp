package kirasoft.weatherapp.presenter;

import android.content.Context;

/**
 * Created by dakotajustin on 11/11/16.
 */

public class MainPresenter {
    /**
     * Required View methods available to Presenter.
     * A passive layer, responsible to show data
     * and receive user interactions
     */
    interface RequiredOps {
        Context getAppContext();
        Context getActivityContext();
        void notifyItemInserted(int layoutPosition);
        void notifyItemRangeChanged(int positionStart, int itemCount);
    }

    /**
     * Operations offered to View to communicate with Presenter.
     * Processes user interactions, sends data requests to Model, etc.
     */
    interface ProvidedPresenterOps {
        // Presenter operations permitted to View
        void clickNewNote(EditText editText);
        // setting up recycler adapter
        int getNotesCount();
        NotesViewHolder createViewHolder(ViewGroup parent, int viewType);
        void bindViewHolder(NotesViewHolder holder, int position);
    }

    /**
     * Required Presenter methods available to Model.
     */
    interface RequiredPresenterOps {
        // Presenter operations permitted to Model
        Context getAppContext();
        Context getActivityContext();
    }

    /**
     * Operations offered to Model to communicate with Presenter
     * Handles all data business logic.
     */
    interface ProvidedModelOps {
        // Model operations permitted to Presenter
        int getNotesCount();
        Note getNote(int position);
        int insertNote(Note note);
        boolean loadData();
    }



}
