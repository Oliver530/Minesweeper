package model.cell;

/**
 * Created by olivergerhardt on 01.09.17.
 */
public interface CellRO {

    boolean isOpened();
    boolean isMineInside();
    boolean isMarkedAsMineByUser();

}
