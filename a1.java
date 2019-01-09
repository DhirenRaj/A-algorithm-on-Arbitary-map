import java.io.*;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.util.*;
import java.lang.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
class PathFinder
{List<List<Integer>> paths=new ArrayList<List<Integer>>();
   static List<List<Integer>> s1=new ArrayList<List<Integer>>();
static List<String> s2=new ArrayList<String>();
static int size=0,spi,epi;
static String sp,ep;
int[][] m;
 public void Input()
 {
     try
     {
String s;
Scanner sc=new Scanner(System.in);
FileReader fr = new FileReader("locations.txt");
BufferedReader br = new BufferedReader(fr);
while((s=br.readLine()).compareTo("END")!=0)
{
StringTokenizer st=new StringTokenizer(s," ");
s2.add(st.nextToken());
List<Integer> temp =new ArrayList<Integer>();
temp.add(Integer.parseInt(st.nextToken()));
temp.add(Integer.parseInt(st.nextToken()));
s1.add(temp);
size++;
}
for(int i=0;i<size-1;i++){
    for(int j=i+1;j<size;j++)

    {
        if(s2.get(i).compareTo(s2.get(j))>0)
        {
            String temp1=s2.get(j);
            s2.set(j,s2.get(i));
            s2.set(i,temp1);
            List<Integer> temp2=s1.get(j);
            s1.set(j,s1.get(i));
            s1.set(i,temp2);

        }
    }
}
 m=new int[size][size];
 fr = new FileReader("connections.txt");
 br = new BufferedReader(fr);
while((s=br.readLine()).compareTo("END")!=0)
{
StringTokenizer st=new StringTokenizer(s," ");
int i=s2.indexOf(st.nextToken());
st.nextToken();
while(st.hasMoreTokens()){
int j=s2.indexOf(st.nextToken());
m[i][j]=1;
m[j][i]=1;
}
}
System.out.println("Available nodes are :\n"+s2);

}catch(Exception e){e.printStackTrace();}
}
public List<Integer> Alternates(int sp,List<Integer> visited)
{
    List<Integer> alt=new ArrayList<Integer>();
    for(int j=0;j<size;j++)
        if(m[sp][j]==1 && visited.indexOf(j)==-1)
        alt.add(j);
    return alt;
}
public void Path(int sp,int ep,List<Integer> visited)
{
    try
     {
         PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
         out.println("The paths are:");
         out.close();
    List<Integer> alt=Alternates(sp,visited);
    for(int i=0;i< alt.size();i++)
    {
        List <Integer> visit= new ArrayList<Integer>();
        visit.addAll(visited);
        visit.add(alt.get(i));
        if(alt.get(i)==ep)
        {
           paths.add(visit);
        }
        else
        {
            Path(alt.get(i),ep,visit);
        }

    }


    }catch(Exception e){e.printStackTrace();}

}
public void Output()
{

    try{
for(int i=0;i<paths.size();i++)
{List<Integer> path=new ArrayList<Integer>();
path=paths.get(i);
double dist,total=0;
for(int j=0;j<path.size()-1;j++){
    String z1=s2.get(path.get(j));
    String z2=s2.get(path.get(j+1));
    dist=distance(path.get(j),path.get(j+1));
    total=total+dist;
    System.out.println(z1+" to "+z2+" Length "+dist);
}
System.out.println("Total path length "+total);
System.out.println("-----------------------------------------");
}

    }catch(Exception e){e.printStackTrace();}
}
public double distance(int a,int b)
{
    double x1,x2,y1,y2,z1,z2,e,f;
    x1=s1.get(a).get(0);
    y1=s1.get(a).get(1);
    x2=s1.get(b).get(0);
    y2=s1.get(b).get(1);
    e=x2-x1;
    f=y2-y1;
    z1=Math.pow(e,2);
    z2=Math.pow(f,2);
    return Math.sqrt((z1+z2));
}
public static void main(String args[])
{
    Scanner sc=new Scanner(System.in);
    PathFinder a=new PathFinder();
    a.Input();
    System.out.println("enter starting point");
sp=sc.next();
spi=s2.indexOf(sp);
System.out.println("enter ending point");
ep=sc.next();
epi=s2.indexOf(ep);
System.out.println("please wait for some time");
    List<Integer> visited= new ArrayList<Integer>();
    visited.add(spi);
    a.Path(spi,epi,visited);
    a.Output();
}
}
