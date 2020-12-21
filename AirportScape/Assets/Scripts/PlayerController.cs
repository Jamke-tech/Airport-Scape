using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class PlayerController : MovingObject
{
    public int timeRemaining;


    private Animator animator;
    
    
    // Start is called before the first frame update
    protected override void Start()
    {
        animator = GetComponent<Animator>();
        timeRemaining = GameManager.instance.playerTime;
        base.Start();
    }

    private void OnDisable()
    {
        GameManager.instance.playerTime = timeRemaining;

    }

    // Update is called once per frame
    void Update()
    {
        int horizontal = 0;
        int vertical = 0;

        horizontal = (int)Input.GetAxisRaw("Horizontal");
        vertical = (int)Input.GetAxisRaw("Vertical");
        
        if (horizontal != 0 || vertical != 0)//mirem si ens estem intentant moure
        {
            if (horizontal != 0)
                vertical = 0;
            if (vertical != 0)
                horizontal = 0;
            AttemptMove(horizontal, vertical);
        }

        //fEM SALTAR ELS TRIGGERS PER LES ANIMACIONS DEL PERSONATGE

        if (horizontal < 0)
        {
            animator.SetBool("Left",true);
            animator.SetBool("Right", false);
            animator.SetBool("Front", false);
            animator.SetBool("Back", false);

        }
        else if (horizontal > 0)
        {
            animator.SetBool("Left", false);
            animator.SetBool("Right", true);
            animator.SetBool("Front", false);
            animator.SetBool("Back", false);
        }
        else if (vertical > 0)
        {
            animator.SetBool("Left", false);
            animator.SetBool("Right", false);
            animator.SetBool("Front", false);
            animator.SetBool("Back", true);
        }
        else if (vertical < 0)
        {
            animator.SetBool("Left", false);
            animator.SetBool("Right", false);
            animator.SetBool("Front", true);
            animator.SetBool("Back", false);
        }
        else
        {
            animator.SetBool("Left", false);
            animator.SetBool("Right", false);
            animator.SetBool("Front", false);
            animator.SetBool("Back", false);

        }



    }

    public void LooseTime(int timeLost)
    {
        timeRemaining = timeRemaining - timeLost;
        //Saltar animacion de daño
        Debug.Log("AUCH");
    }



}
