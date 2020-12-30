using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public abstract class MovingObject : MonoBehaviour
{
    public float moveTime = 0.1f;
    public int velocidad;
    public LayerMask blockinLayer; // per veure si el espai on ens movem esta ocupat o no 

    private BoxCollider2D boxCollider;
    private Rigidbody2D rb2D;
    private float inverseMoveTime;


    // Start is called before the first frame update
    protected virtual void Start()
    {
        boxCollider = GetComponent<BoxCollider2D>();
        rb2D = GetComponent<Rigidbody2D>();
        inverseMoveTime = 1f / moveTime;
        velocidad = 25;

    }

    protected bool Move(int xDir, int yDir, out RaycastHit2D hit)
    {
        Vector2 start = transform.position;
        Vector2 end = start + new Vector2(xDir, yDir);
        boxCollider.enabled = false;
        hit = Physics2D.Linecast(start, end, blockinLayer);
        boxCollider.enabled = true;

        if (hit.transform == null)//mirem si hem xocat
        {
            StartCoroutine(Movement(new Vector3(xDir, yDir, 0f)));
            return true;
        }
        return false;
    }

    protected virtual void AttemptMove(int xDir, int yDir)
       
    {
        RaycastHit2D hit;
        bool canMove = Move(xDir, yDir, out hit);

        //StartCoroutine(Movement(new Vector3(xDir, yDir, 0f)));
    }

    protected IEnumerator Movement (Vector3 inputplayer)
    {
        rb2D.MovePosition(GetComponent<Transform>().position + inputplayer * velocidad * Time.deltaTime);

        yield return null;
    }
    protected IEnumerator SmoothMovement(Vector3 end)
    {
        float sqrRemainingDistance = (transform.position - end).sqrMagnitude;
        while (sqrRemainingDistance > float.Epsilon)
        {
            Vector3 newPosition = Vector3.MoveTowards(rb2D.position, end, inverseMoveTime * Time.deltaTime);
            rb2D.MovePosition(newPosition);
            sqrRemainingDistance = (transform.position - end).sqrMagnitude;
            yield return null; // ens esperem un frame per fer la modificacio de la posicio
        }
    }


}
