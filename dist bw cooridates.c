#include<stdio.h>
#include<math.h>

main()
{
    int x1,x2,y1,y2;
    float distance;

    printf("Enter X1 and Y1:");
    scanf("%d%d",&x1,&y1);
    printf("Enter x2 and Y2:");
    scanf("%d%d",&x2,&y2);

    distance = sqrt(pow((x2-x1),2)+pow((y2-y1),2));

    printf("The distance is %f",distance);

}
