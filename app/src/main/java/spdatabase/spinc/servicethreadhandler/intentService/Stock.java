package spdatabase.spinc.servicethreadhandler.intentService;

import android.os.Parcel;
import android.os.Parcelable;

public class Stock implements Parcelable {

    private String symbol;

    // Threshold value
    private double askValue;
    private double bidValue;

    public Stock() {}

    public Stock(String symbol, double askValue, double bidValue) {
        this.symbol = symbol;
        this.askValue = askValue;
        this.bidValue = bidValue;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }


    public double getAskValue() {
        return askValue;
	}

	public void setAskValue(double askValue) {
		this.askValue = askValue;
	}

	public double getBidValue() {
		return bidValue;
	}

	public void setBidValue(double bidValue) {
		this.bidValue = bidValue;
	}

	@Override
    public void writeToParcel(Parcel dest, int flags) {
        // We write the stock information in the parcel
        dest.writeString(symbol);
        dest.writeDouble(askValue);
        dest.writeDouble(bidValue);
    }

    public static final Creator<Stock> CREATOR = new Creator<Stock>() {

        @Override
        public Stock createFromParcel(Parcel source) {
            Stock stock = new Stock();
            stock.setSymbol(source.readString());
            stock.setAskValue(source.readDouble());
            stock.setBidValue(source.readDouble());
            return stock;
        }

        @Override
        public Stock[] newArray(int size) {
            return new Stock[0];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}