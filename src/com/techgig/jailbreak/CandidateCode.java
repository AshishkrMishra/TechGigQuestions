package com.techgig.jailbreak;

/**
 * Created by ashish on 19/4/17.
 */
public class CandidateCode {
   public static void main(String ...args)
    {
            int input1=5;
            int input2=1;
            int height[]={9,10};
            System.out.println(CandidateCode.GetJumpCount(input1,input2,height));
    }

    /**
     * GetJumpCount - Will give no of Jump Attempts of Prisoner
     * @param input1-  Height prisoner can Climb in one Attempt
     * @param input2 - Height prisoner slips in each Attempt
     * @param input3 - All Walls Height he need to cross
     * @return int it will no of jump/Attempt he need to make the climb All Walls
     */
    static int GetJumpCount(int input1,int input2,int[] input3)
    {

        int tottalJump=0;
        for (int height:input3)
        {
            tottalJump+=GetJumpCountUtil(input1,input2,height);
        }
        return tottalJump;
    }

    /**
     *  GetJumpCountUtil -- No of Jumps Required to Cross Wall
     * @param input1 -Height prisoner can Climb in one Attempt
     * @param input2 -Height prisoner slips in each Attempt
     * @param height - Height of Wall
     * @return No Of Jumps to Cross Wall
     */
    static int GetJumpCountUtil(int input1,int input2,int height)
    {
        int noOfJump=0;
        int heightDiff=input1-input2;
        int currentHeight=height;
        if(input1>height)
        {
            noOfJump=1;
        }
        else if(heightDiff>0)
        {
            while (currentHeight>input1)
            {
                currentHeight-=heightDiff;
                noOfJump++;
            }
            if(currentHeight<=input1)
            {
                noOfJump++;
            }
        }
        return noOfJump;
    }




}
