using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Thief : MonoBehaviour
{
    public int speed;
    private Rigidbody2D rb2D;
    private Transform target;
    private float towardsX;
    private float towardsY;

    private Animator animator;

    // Start is called before the first frame update
    void Start()
    {
        animator = GetComponent<Animator>();
        target = GameObject.FindGameObjectWithTag("Player").GetComponent<Transform>();
        rb2D = GetComponent<Rigidbody2D>();
        speed = 8;
    }

    // Update is called once per frame
    void Update()
    {
        //Calculem les posicions x i y del objectiu
        towardsX = Mathf.MoveTowards(transform.position.x, target.position.x, speed * Time.deltaTime);
        towardsY = Mathf.MoveTowards(transform.position.y, target.position.y, speed * Time.deltaTime);

        rb2D.MovePosition(new Vector3(towardsX,towardsY,0f));

        float horizontal = target.position.x - transform.position.x;
        float vertical = target.position.y - transform.position.y;




        if (horizontal < 0)
        {
            animator.SetBool("Left", true);
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
        else //if (towardsY < 0)
        {
            animator.SetBool("Left", false);
            animator.SetBool("Right", false);
            animator.SetBool("Front", true);
            animator.SetBool("Back", false);
        }


    }
}
