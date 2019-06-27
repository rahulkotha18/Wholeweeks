import java.util.Scanner;
import java.util.HashMap;
import java.io.*;
class Solution
{
    public int solution(int Y,String A,String B,String W)
    {
        int exactdays=0,odds=0,lags=0,weeks=0;
        int a[]={0,3,0,3,2,3,2,3,3,2,3,2,3};
        int fday     = first_day(W);
        int start    = getMonth(A);
        int end      = getMonth(B);
        boolean leap = isLeap(Y);

        if (leap)
            a[2]=1;               // leap year feb has 29 days so odd days in feb=1

        for(int i=1;i<start;i++)  // count total odd days and find what day is the 1st of starting month
            odds+=a[i];
        odds=odds%7;
        int  startday=(fday+odds)%7;
        exactdays=getdays(start);

        if (startday!=1)          // exactdays = after omiting first few days to go  to first monday of the starting month
            exactdays-=(8-startday);

       weeks+=exactdays/7;        // number of weeks in first month 
       lags=exactdays%7;          // lag / left over days after exact weeks in the first month
       
       for(int i=start+1;i<=end;i++) // for loop- runs from second month to ending month
       {
           exactdays=getdays(i)+lags; //adding the lag days from previous month since last week is incomplete in that month
           if (i==2 && leap)        // condition for leap year
                exactdays++;
           weeks+=exactdays/7;       //getting exact weeks in current month
           lags=exactdays%7;        // getting lag / left over days in current month
       }
    return weeks;               // total weeks
    }

    public boolean isLeap(int year)
    {
        return year%4==0;
    }

    public int first_day(String day)
    {
        HashMap<String,Integer> hm=new HashMap<>();
        hm.put("Monday",1);
        hm.put("Tuesday",2);
        hm.put("Wednesday",3);
        hm.put("Thursday",4);
        hm.put("Friday",5);
        hm.put("Saturday",6);
        hm.put("Sunday",7);
        return (int)hm.get(day);
    }

    public int getMonth(String month)
    {
        HashMap<String,Integer> hm=new HashMap<>();
        hm.put("Jan",1);
        hm.put("Feb",2);
        hm.put("Mar",3);
        hm.put("Apr",4);
        hm.put("May",5);
        hm.put("Jun",6);
        hm.put("Jul",7);
        hm.put("Aug",8);
        hm.put("Sep",9);
        hm.put("Oct",10);
        hm.put("Nov",11);
        hm.put("Dec",12);
        return (int)hm.get(month);
    }

    public int getdays(int month)
    {
        HashMap<Integer,Integer> hm=new HashMap<>();
        hm.put(1,31);
        hm.put(2,28);
        hm.put(3,31);
        hm.put(4,30);
        hm.put(5,31);
        hm.put(6,30);
        hm.put(7,31);
        hm.put(8,31);
        hm.put(9,30);
        hm.put(10,31);
        hm.put(11,30);
        hm.put(12,31);
        return (int)hm.get(month);
    }


    public static void main(String...args)
    {
        try{
        System.out.println("Enter year, start_month, end_month, first_day\n (give months in short forms)\n eg: 2019 Mar Jul Wednesday");
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String line[]=br.readLine().split(" ");
       // Scanner sc=new Scanner(System.in);
        int year=Integer.parseInt(line[0]);
        String start=line[1];
        String end=line[2];
        String day=line[3];
        Solution obj=new Solution();
        System.out.println(obj.solution(year,start,end,day));
        }
        catch(Exception e)
        {
            System.out.println("give proper inputs : follow eg pattern");

        }
    }
}
